package com.alanford.carpediem

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class CarpediemApplication {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(CarpediemApplication::class.java, *args)
		}
	}

	/**
	 * This method should return the quote of the day
	 * @return The quote of the day
	 */
	@GetMapping("/")
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