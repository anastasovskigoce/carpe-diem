package com.alanford.carpediem.controllers

import com.alanford.carpediem.models.Quote
import com.alanford.carpediem.models.QuoteUnderReview
import com.alanford.carpediem.services.QuoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.random.Random

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
    @GetMapping("/top-quotes")
    fun getTopFifteenQuotes(): List<Quote> = quoteService.getAllQuotes().sortedByDescending { it.rating }.take(15)

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
    @GetMapping("/quote-by-author")
    fun getQuoteByAuthor(@RequestParam(value = "authorName", defaultValue = "Anonymous") authorName: String): List<Quote> {
        return quoteService.getQuotesByAuthor(authorName).sortedByDescending { it.rating }
    }

    /**
     * Reduces the rating of a quote
     * @param id the ID of the quote
     */
    @PutMapping("/up-vote-quote")
    fun upVote(@RequestParam(value = "id") id: String): ResponseEntity.BodyBuilder {
        val quote = quoteService.getQuoteById(id)
        return if (quote != null) {
            quoteService.upOrDownVote(Quote(id,
                    quote.quote,
                    quote.author,
                    quote.rating + 1)
            )
            ResponseEntity.ok()
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST)
        }
    }

    /**
     * Increases the rating of a quote
     * @param id the ID of the quote
     */
    @PutMapping("/down-vote-quote")
    fun downVote(@RequestParam(value = "id") id: String): ResponseEntity.BodyBuilder {
        val quote = quoteService.getQuoteById(id)
        return if (quote != null) {
            quoteService.upOrDownVote(Quote(id,
                    quote.quote,
                    quote.author,
                    quote.rating - 1)
            )
            ResponseEntity.ok()
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST)
        }
    }

    /**
     * Return a list of quotes by an author
     */
    @GetMapping("/generate-rating")
    private fun generateRandomRating() {
        //We don't want this publicly available
//        val allQuotes = quoteService.getAllQuotes()
//
//        allQuotes.forEach {
//            quoteService.generateRandomRating(Quote(
//                    id = it.id,
//                    quote = it.quote,
//                    author = it.author,
//                    rating = rand(10, 1000).toDouble()
//            ))
//        }
    }

    private fun rand(start: Int, end: Int): Int {
        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(end - start + 1) + start
    }

    /**
     * Removes all duplicate quotes
     */
    @GetMapping("/remove-duplicates")
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