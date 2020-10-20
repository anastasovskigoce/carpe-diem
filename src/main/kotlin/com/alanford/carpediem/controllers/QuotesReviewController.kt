package com.alanford.carpediem.controllers

import com.alanford.carpediem.models.Quote
import com.alanford.carpediem.models.QuoteUnderReview
import com.alanford.carpediem.services.QuoteReviewService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * This class handles the interaction between the application and the database
 */
@RestController
class QuotesReviewController {

    @Autowired
    lateinit var quoteReviewService: QuoteReviewService

    /**
     * Call this service if you want to add a quote. The quote will be reviewed
     * before it is added to the main quote list
     * @param quote the quote that you want to add
     */
    @PostMapping("/quote")
    fun addQuote(@RequestBody quote: Quote) {
        quoteReviewService.addQuote(
                QuoteUnderReview(quote.id,
                        quote.quote,
                        quote.author)
        )
    }
}