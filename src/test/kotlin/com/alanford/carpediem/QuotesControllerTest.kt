package com.alanford.carpediem

import com.alanford.carpediem.controllers.QuotesController
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.Assert

@SpringBootTest
internal class QuotesControllerTest {

    @Test
    fun listOfTopThirtyQuotes() {
        val controller = QuotesController()

        val result = controller.listOfTopThirtyQuotes()

        Assert.hasText(result, "To be or not to be, that is the question")
    }

    @Test
    fun getQuote() {
        val controller = QuotesController()

        val result = controller.getQuote("2")

        Assert.hasText(result, "The requested quote id is 2!")
    }
}