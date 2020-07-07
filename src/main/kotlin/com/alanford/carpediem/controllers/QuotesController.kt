package com.alanford.carpediem.controllers

import com.alanford.carpediem.models.Quote
import com.alanford.carpediem.services.QuoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * This class handles the interaction between the application and the database
 */
@RestController
class QuotesController {

    @Autowired
    lateinit var quoteService: QuoteService

    /**
     * This returns all the quotes
     * @return all the available quotes
     */
    @GetMapping("/quotes")
    fun listOfAllQuotes(): List<Quote> = quoteService.getAllQuotes()

    /**
     * Return a particular quote
     * @param id the ID of the quote
     * @return a particular quote
     */
    @GetMapping("/quote")
    fun getQuote(@RequestParam(value = "id", defaultValue = "1") id: String) = quoteService.getQuoteById(id)
}