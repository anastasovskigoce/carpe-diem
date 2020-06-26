package com.alanford.carpediem

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.Assert

@SpringBootTest
internal class CarpediemApplicationTest {

    @Test
    fun listOfTociwpThirtyQuotes() {
        val application = CarpediemApplication()

        val result = application.listOfTopThirtyQuotes()

        Assert.hasText(result, "To be or not to be, that is the question")
    }

    @Test
    fun getQuote() {
        val application = CarpediemApplication()

        val result = application.getQuote("2")

        Assert.hasText(result, "The requested quote id is 2!")
    }
}