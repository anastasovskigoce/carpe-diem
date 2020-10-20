package com.alanford.carpediem.repository

import com.alanford.carpediem.models.SubscriptionToNotification
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SubscriptionRepository : MongoRepository<SubscriptionToNotification, String>