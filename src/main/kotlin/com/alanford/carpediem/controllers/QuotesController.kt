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

    /**
     * Return a list of quotes by an author
     * @param authorName the name of the author
     */
    @GetMapping("/quoteByAuthor")
    fun getQuoteByAuthor(@RequestParam(value = "authorName", defaultValue = "Anonymous") authorName: String): List<Quote> {
        return quoteService.getQuotesByAuthor(authorName)
    }

    /**
     * Removes all duplicate quotes
     */
    @GetMapping("/removeDuplicates")
    fun removeDuplicates() {
        val allQuotes = quoteService.getAllQuotes()
        val mapOfQuotes = HashMap<String, String?>()
        val duplicatesToRemove = mutableListOf<String?>()

        allQuotes.forEach { quote ->
            // if the map already contains this quote add it to the duplicatesToRemove list
            if (mapOfQuotes.containsKey(quote.quote)) {
                duplicatesToRemove.add(quote.id)
            } else {
                //otherwise add the quote to the map
                mapOfQuotes[quote.quote] = quote.id
            }
        }

        //delete all duplicates
        duplicatesToRemove.forEach { duplicateQuote ->
            if (duplicateQuote != null) {
                quoteService.deleteQuote(duplicateQuote)
            }
        }
    }
}