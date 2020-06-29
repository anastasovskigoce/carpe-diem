package com.alanford.carpediem

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration

@SpringBootApplication
@EnableAutoConfiguration(exclude = [MongoAutoConfiguration::class])
class CarpediemApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(CarpediemApplication::class.java, *args)
        }
    }
}