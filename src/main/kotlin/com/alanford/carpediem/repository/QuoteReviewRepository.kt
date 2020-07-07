package com.alanford.carpediem.repository

import com.alanford.carpediem.models.QuoteUnderReview
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Use this repository to interact with the QuoteReview collection
 */
@Repository
interface QuoteReviewRepository : MongoRepository<QuoteUnderReview, String>
