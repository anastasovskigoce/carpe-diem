package com.alanford.carpediem.services

import com.alanford.carpediem.models.NotificationParameter
import com.alanford.carpediem.models.PushNotificationRequest
import com.alanford.carpediem.models.SubscriptionToNotification
import com.alanford.carpediem.repository.SubscriptionRepository
import com.alanford.carpediem.services.PushNotificationService.Constants.AUTHOR
import com.alanford.carpediem.services.PushNotificationService.Constants.QUOTE_ID
import com.alanford.carpediem.services.PushNotificationService.Constants.QUOTE_TEXT
import com.alanford.carpediem.services.PushNotificationService.Constants.TASK_RATE_ONE_HOUR_IN_MILLISECONDS
import com.alanford.carpediem.services.PushNotificationService.Constants.TIME_TO_LIVE
import com.google.firebase.messaging.*
import java.time.temporal.ChronoUnit.HOURS
import java.time.temporal.ChronoUnit.WEEKS
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalTime
import java.util.*
import java.util.concurrent.ExecutionException


@Service
class PushNotificationService {

    object Constants {
        const val TASK_RATE_ONE_HOUR_IN_MILLISECONDS = 3600000L
        const val TIME_TO_LIVE = 2L
        const val QUOTE_TEXT = "QUOTE_TEXT"
        const val AUTHOR = "AUTHOR"
        const val QUOTE_ID = "QUOTE_ID"
    }

    private val logger: Logger = LoggerFactory.getLogger(PushNotificationService::class.java)

    @Autowired
    private lateinit var subscriptionRepository: SubscriptionRepository

    @Autowired
    private lateinit var quoteService: QuoteService

    @Scheduled(fixedRate = TASK_RATE_ONE_HOUR_IN_MILLISECONDS)
    fun sendPushNotification() {
        try {
            getCustomersForNotifications().forEach { customerForNotification ->
                val quote = quoteService.getQuoteForNotification()
                val pushData: MutableMap<String, String> = HashMap()
                pushData[QUOTE_TEXT] = quote.quote
                pushData[AUTHOR] = quote.author
                pushData[QUOTE_ID] = quote.id ?: "ErrorGettingId"

                // TODO add different language support here
                val request = PushNotificationRequest(title = "New Carpe Diem",
                        message = "There is a new Carpe Diem waiting for you!",
                        topic = "",
                        token = customerForNotification.id)

                sendMessage(pushData, request)
                // update last time we sent a notification
                subscriptionRepository.save(customerForNotification.copy(lastTimeNotificationSent = LocalTime.now()))
            }
        } catch (e: InterruptedException) {
            logger.error(e.message)
        } catch (e: ExecutionException) {
            logger.error(e.message)
        }
    }

    fun subscribeToNotification(subscription: SubscriptionToNotification) = subscriptionRepository.save(subscription)

    fun unsubscribeToNotification(subscription: SubscriptionToNotification) = subscriptionRepository.save(subscription)

    fun getSubscription(id: String) = subscriptionRepository.findByIdOrNull(id)

    fun getCustomersForNotifications(): List<SubscriptionToNotification> {

        // will have all the time zones where it is around 10 AM or 7 PM
        val timeZones = mutableListOf<String>()

        TimeZone.getAvailableIDs().forEach { timezoneId ->
            val timeZone: TimeZone =  TimeZone.getTimeZone(timezoneId)
            val zonesLocalTime: LocalTime = LocalTime.now(timeZone.toZoneId())
            // if the local time in the zone is around 10 AM
            if (zonesLocalTime.isAfter(LocalTime.of(9, 30)) && zonesLocalTime.isBefore(LocalTime.of(10, 30))) {
                timeZones.add(timezoneId)
            }

            // if the local time in the zone is around 7 PM
            if (zonesLocalTime.isAfter(LocalTime.of(18, 30)) && zonesLocalTime.isBefore(LocalTime.of(19, 30))) {
                timeZones.add(timezoneId)
            }
        }

        // all subscriptions in the timezones where it is around 10 AM or 7 PM
        val subscriptions = subscriptionRepository.findAll().filter { it.isSubscribe && it.timezoneId in timeZones }
        val subscriptionsDaily = subscriptions.filter { it.lastTimeNotificationSent.until(LocalTime.now(), HOURS) >= 24 }
        val subscriptionsWeekly = subscriptions.filter { it.lastTimeNotificationSent.until(LocalTime.now(), WEEKS) >= 1 }

        return subscriptionsDaily + subscriptionsWeekly
    }

    @Throws(InterruptedException::class, ExecutionException::class)
    fun sendMessage(data: Map<String, String>, request: PushNotificationRequest) {
        val message = getPreconfiguredMessageWithData(data, request)
        val response = sendAndGetResponse(message)
        logger.info("Sent message with data. Topic: " + request.topic + ", response = " + response + ", message = " + message)
    }

    @Throws(InterruptedException::class, ExecutionException::class)
    private fun sendAndGetResponse(message: Message): String {
        return FirebaseMessaging.getInstance().sendAsync(message).get()
    }

    private fun getPreconfiguredMessageWithData(data: Map<String, String>, request: PushNotificationRequest): Message {
        val androidConfig = getAndroidConfig(request.topic)
        val apnsConfig = getApnsConfig(request.topic)
        return Message.builder()
                .setApnsConfig(apnsConfig)
                .setAndroidConfig(androidConfig)
                .setNotification(
                        Notification(
                                request.title,
                                request.message
                        )
                )
                .putAllData(data)
                .setToken(request.token)
                .build()
    }

    private fun getAndroidConfig(topic: String): AndroidConfig {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(TIME_TO_LIVE).toMillis())
                .setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder()
                        .setSound(NotificationParameter.SOUND.value)
                        .setColor(NotificationParameter.COLOR.value)
                        .setTag(topic)
                        .build()
                ).build()
    }

    //Apple config
    private fun getApnsConfig(topic: String): ApnsConfig {
        return ApnsConfig.builder()
                .setAps(
                        Aps.builder()
                                .setCategory(topic)
                                .setThreadId(topic)
                                .build()
                ).build()
    }
}