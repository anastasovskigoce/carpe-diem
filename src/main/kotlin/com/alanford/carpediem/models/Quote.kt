package com.alanford.carpediem.models

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * TODO add description
 */
@Document(collection = "quotes")
data class Quote(
        @Id
        val id: String,
        val quote: String,
        val author: String
)