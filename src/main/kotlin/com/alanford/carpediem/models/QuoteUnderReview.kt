package com.alanford.carpediem.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * A quote that is under review to be added, updated or removed from the main list
 */
@Document(collection = "quote-review")
data class QuoteUnderReview(
        @Id
        val id: String?,
        val quote: String = "",
        val author: String = ""
)