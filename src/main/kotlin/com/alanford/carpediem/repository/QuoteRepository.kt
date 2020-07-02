package com.alanford.carpediem.repository

import com.alanford.carpediem.models.Quote
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Use this repository to interact with the Quote collection
 */
@Repository
interface QuoteRepository : MongoRepository<Quote, String>