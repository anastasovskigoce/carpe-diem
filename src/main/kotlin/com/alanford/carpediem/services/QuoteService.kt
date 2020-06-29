package com.alanford.carpediem.services

import com.alanford.carpediem.models.Quote
import com.alanford.carpediem.repository.QuoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

/**
 * TODO add comment here
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
     * Returns a a quote with the specified ID
     * @param id the ID of the quote you want to retrieve
     */
    fun getQuoteById(id: String) = quoteRepository.findByIdOrNull(id)

    /**
     * Adds a new quote
     * @param quote the quote you want to add
     */
    fun addQuote(quote: Quote) = quoteRepository.save(quote)

    /**
     * Updates an existing quote. If the quote does not exist it will be added
     * @param the quote that you want to update
     */
    fun updateQuote(quote: Quote) = quoteRepository.save(quote)

    /**
     * Deletes a particular quote
     * @param id the ID of quote you would like to delete
     */
    fun deleteQuote(id: String) = quoteRepository.deleteById(id)
}