package com.alanford.carpediem.controllers

import com.alanford.carpediem.models.Quote
import com.alanford.carpediem.services.QuoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

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

    /**
     * Call this service if you want to add a quote
     * @param quote the qoute that you want to add
     */
    @PostMapping("/quote")
    fun addQuote(@RequestBody quote: Quote) = quoteService.addQuote(quote)

    /**
     * Call this if you want to update a quote
     * @param quote the quote you would like to update
     */
    @PutMapping("/quote")
    fun updateQuote(@RequestBody quote: Quote) = quoteService.updateQuote(quote)

    /**
     * Call this if you want to delete a particular quote
     * @param id the ID of the quote you want to delete
     */
    @DeleteMapping("/quote")
    fun deleteQuote(@RequestParam(value = "id") id: String) = quoteService.deleteQuote(id)
}