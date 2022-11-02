package com.example.nbu

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class NbuApplication

fun main(args: Array<String>) {
	runApplication<NbuApplication>(*args)
}
