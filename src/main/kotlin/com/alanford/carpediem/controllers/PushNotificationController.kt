package com.alanford.carpediem.controllers

import com.alanford.carpediem.models.PushNotificationResponse
import com.alanford.carpediem.services.PushNotificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Used for testing only
 */
@RestController
class PushNotificationController {

//    @Autowired
//    lateinit var pushNotificationService: PushNotificationService

//    @GetMapping("/notification")
//    fun sendSampleNotification(): ResponseEntity<*>? {
//        pushNotificationService.sendPushNotification()
//        return ResponseEntity(PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK)
//    }
}