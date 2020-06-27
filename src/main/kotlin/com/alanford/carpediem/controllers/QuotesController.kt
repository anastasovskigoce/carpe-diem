package com.alanford.carpediem.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class QuotesController {
    /**
     * This method should return the quote of the day
     * @return The quote of the day
     */
    @GetMapping("/quotes")
    fun listOfTopThirtyQuotes(): String {
        //TODO this needs to be read from the DB or some data store
        return "To be or not to be, that is the question"
    }

    /**
     * Return a particular quote
     * @param id the ID of the quote
     * @return a particular quote
     */
    @GetMapping("/quote")
    fun getQuote(@RequestParam(value = "id", defaultValue = "1") id: String?): String {
        //Not really sure if we need  this method
        return String.format("The requested quote id is %s!", id)

    }
}