package com.alanford.carpediem.services

import com.alanford.carpediem.models.QuoteUnderReview
import com.alanford.carpediem.repository.QuoteReviewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *  Service exposes functions that the controller can use to interact with the database
 */
@Service
class QuoteReviewService {

    @Autowired
    lateinit var quoteReviewRepository: QuoteReviewRepository

    /**
     * Adds a new quote
     * @param quote the quote you want to add
     */
    fun addQuote(quote: QuoteUnderReview) = quoteReviewRepository.save(quote)
}