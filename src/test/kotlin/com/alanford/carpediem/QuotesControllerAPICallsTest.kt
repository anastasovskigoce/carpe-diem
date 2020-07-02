package com.alanford.carpediem

import com.alanford.carpediem.controllers.QuotesController
import com.alanford.carpediem.models.Quote
import com.alanford.carpediem.services.QuoteService
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(QuotesController::class)
class QuotesControllerAPICallsTest {

    @Autowired
    lateinit var subjectUnderTest: MockMvc

    @MockBean
    lateinit var service: QuoteService

    @Test
    @Throws(Exception::class)
    fun `verify that quotes api call returns a list of quotes`() {
        `when`(service.getAllQuotes()).thenReturn(listOf(
                Quote("1", "Quote 1", "Author 1"),
                Quote("2", "Quote 2", "Author 2")
        )
        )

        val result = subjectUnderTest.perform(get("/quotes")).andDo(print())

        result.andExpect(status().isOk)
        result.andExpect(content().string(containsString("1")))
        result.andExpect(content().string(containsString("2")))
        result.andExpect(content().string(containsString("Quote 1")))
        result.andExpect(content().string(containsString("Quote 2")))
        result.andExpect(content().string(containsString("Author 1")))
        result.andExpect(content().string(containsString("Author 2")))
    }

    @Test
    @Throws(Exception::class)
    fun `verify that quote api call returns a particular quote`() {
        `when`(service.getQuoteById("1")).thenReturn(Quote("1", "Quote 1", "Author 1"))

        val result = subjectUnderTest.perform(get("/quote?id=1")).andDo(print())

        result.andExpect(status().isOk)
        result.andExpect(content().string(containsString("1")))
        result.andExpect(content().string(containsString("Quote 1")))
        result.andExpect(content().string(containsString("Author 1")))
    }
}