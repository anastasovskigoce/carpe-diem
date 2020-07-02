package com.alanford.carpediem

import com.alanford.carpediem.controllers.QuotesController
import com.alanford.carpediem.services.QuoteService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc


@SpringBootTest
internal class QuotesControllerTest {

    @Autowired
    lateinit var subjectUnderTest: QuotesController

    @Test
    @Throws(Exception::class)
    fun `Verify that the context loads`() {
        assertThat(subjectUnderTest).isNotNull()
    }


//    @Test
//    @Throws(Exception::class)
//    fun `verify that get all quotes returns a list of quotes`() {
//        val listOfQuotes = listOf(
//                Quote("1", "Word 1", "Author 1"),
//                Quote("2", "Word 2", "Author 2")
//        )
//        `when`(quoteService.getAllQuotes()).thenReturn(listOfQuotes)
//
//        val resultAction = mockMvc.perform(get("/quotes")).andDo(print())
//
//        resultAction.andExpect(status().isOk)
//        resultAction.andExpect(content().string(containsString("Hello, Mock")))
//    }
}