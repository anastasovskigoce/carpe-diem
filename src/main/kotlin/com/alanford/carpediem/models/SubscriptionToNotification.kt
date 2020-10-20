package com.alanford.carpediem.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalTime

@Document(collection = "subscription")
data class SubscriptionToNotification(
        @Id
        val id: String,
        // do you want to get notifications daily or weekly
        val frequency: NotificationFrequency,
        // do you want to get notifications in the morning or in the evening
        val timeOfDay: NotificationTime,
        // the customers timezone
        val timezoneId: String,
        // are they subscribed to notifications
        val isSubscribe: Boolean,
        // the last time a notifications was sent
        var lastTimeNotificationSent: LocalTime = LocalTime.now()
)