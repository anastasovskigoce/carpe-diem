package com.alanford.carpediem.controllers

import com.alanford.carpediem.models.SubscriptionToNotification
import com.alanford.carpediem.services.PushNotificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * This class handles subscribing to notifications
 */
@RestController
class SubscribeToNotificationsController {

    @Autowired
    lateinit var subscribeService: PushNotificationService

    /**
     * Subscribes a user to get a notification about a quote
     */
    @PostMapping("/subscribe")
    fun subscribeToNotification(@RequestBody subscription: SubscriptionToNotification) {
        subscribeService.subscribeToNotification(subscription.copy(isSubscribe = true))
    }

    /**
     * Unsubscribe a user from a notification
     */
    @PutMapping("/unsubscribe")
    fun unsubscribeToNotification(@RequestParam(value = "id") id: String): ResponseEntity.BodyBuilder {
        val subscription = subscribeService.getSubscription(id)
        return if (subscription != null) {
            subscribeService.unsubscribeToNotification(subscription.copy(isSubscribe = false))
            ResponseEntity.ok()
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST)
        }
    }
}