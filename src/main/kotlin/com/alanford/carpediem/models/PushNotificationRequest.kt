package com.alanford.carpediem.models

class PushNotificationRequest(
        var title: String,
        var message: String,
        var topic: String,
        var token: String
)
