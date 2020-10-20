package com.alanford.carpediem.services

import com.alanford.carpediem.models.Quote
import com.alanford.carpediem.repository.QuoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 *  Service exposes functions that the controller can use to interact with the database
 */
@Service
class QuoteService {

    @Autowired
    private lateinit var quoteRepository: QuoteRepository

    /**
     * Get quote for notification
     */
    fun getQuoteForNotification(): Quote {
        // get all the quotes
        val listOfAllQuotes = getAllQuotes()
        // get the first one that has not been used
        var quoteResult: Quote? = listOfAllQuotes.firstOrNull { !it.quoteUsedInNotification }

        // we have found a quote we have not used before
        if(quoteResult != null) {
            quoteRepository.save(quoteResult.copy(quoteUsedInNotification = true))
            return quoteResult
        }

        // update quoteUsedInNotification for all quotes to not be false (reuse quotes)
        listOfAllQuotes.forEach { quote -> quoteRepository.save(quote.copy(quoteUsedInNotification = false)) }
        // we used all the available quotes, repeat the list
        quoteResult = listOfAllQuotes[0]
        return quoteResult
    }


    /**
     * Returns all the quotes
     */
    fun getAllQuotes() = quoteRepository.findAll().toList()

    /**
     * Returns a quote with the specified ID
     * @param id the ID of the quote you want to retrieve
     */
    fun getQuoteById(id: String) = quoteRepository.findByIdOrNull(id)

    /**
     * Returns all quotes by a particular author
     * @param authorName the name of the author for which you want the quotes
     */
    fun getQuotesByAuthor(authorName: String): List<Quote> {
        return quoteRepository.findAll().filter { it.author.contains(authorName, true) }
    }

    /**
     * Increases or decreases the rating of a quote
     * @param quote the quote with increased rating
     */
    fun upOrDownVote(quote: Quote) = quoteRepository.save(quote)

    fun generateRandomRating(quote: Quote) {
        quoteRepository.save(quote)
    }

    /**
     * Deletes a particular quote
     * @param id the ID of quote you would like to delete
     */
    fun deleteQuote(id: String) = quoteRepository.deleteById(id)
}