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
    lateinit var quoteRepository: QuoteRepository

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
     * Deletes a particular quote
     * @param id the ID of quote you would like to delete
     */
    fun deleteQuote(id: String) = quoteRepository.deleteById(id)
}