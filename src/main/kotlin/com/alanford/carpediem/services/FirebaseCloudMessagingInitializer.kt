package com.alanford.carpediem.services

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct
import java.io.IOException

@Service
class FirebaseCloudMessagingInitializer {
    @Value("google/push-notifications-carpediem-firebase-adminsdk.json")
    private lateinit var firebaseConfigPath: String

    private val logger = LoggerFactory.getLogger(FirebaseCloudMessagingInitializer::class.java)

    @PostConstruct
    fun initialize() {
        try {
            val options = FirebaseOptions.Builder()
                    .setCredentials(
                            GoogleCredentials.fromStream(ClassPathResource(firebaseConfigPath).inputStream)
                    ).build()
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options)
                logger.info("Firebase application has been initialized")
            }
        } catch (error: IOException) {
            logger.error(error.message)
        }
    }
}