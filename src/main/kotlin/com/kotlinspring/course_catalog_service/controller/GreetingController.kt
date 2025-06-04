package com.kotlinspring.course_catalog_service.controller

import com.kotlinspring.course_catalog_service.services.GreetingService

import io.github.oshai.kotlinlogging.KotlinLogging

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/greetings")
class GreetingController (
    private val greetingService: GreetingService
){

    data class GreetingResponse(val message: String)

    private val logger = KotlinLogging.logger {}

    @GetMapping("/{name}")
    fun receiveGreeting(@PathVariable("name") name: String): ResponseEntity<GreetingResponse> {
        return try {
            val payload = greetingService.getGreeting(name )
            logger.info { "hello $name from logger" }
            ResponseEntity.ok(GreetingResponse(payload))
        }catch (ex: IllegalArgumentException){
            ResponseEntity.badRequest().body(GreetingResponse(ex.message?:"Invalid Input"))
        }
    }
}