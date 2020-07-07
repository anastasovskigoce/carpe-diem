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
                        quote.author,
                        "Add")
        )
    }

    /**
     * Call this if you want to update a quote. The quote will be reviewed before it is
     * added to the main quote list
     * @param quote the quote you would like to update
     */
    @PutMapping("/quote")
    fun updateQuote(@RequestBody quote: Quote, @RequestParam(defaultValue = "1") quoteId: String) {
        quoteReviewService.addQuote(QuoteUnderReview(quote.id,
                quote.quote,
                quote.author,
                "Update",
                existingQuoteId = quoteId)
        )
    }

    /**
     * Call this if you want to delete a particular quote.
     * The quote will be reviewed before it is deleted
     * @param id the ID of the quote you want to delete
     */
    @DeleteMapping("/quote")
    fun deleteQuote(@RequestParam(defaultValue = "1") quoteId: String) {
        quoteReviewService.addQuote(QuoteUnderReview(id = null, mapping = "Delete",
                existingQuoteId = quoteId))
    }
}