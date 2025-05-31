package com.kotlinspring.course_catalog_service.services
import org.springframework.stereotype.Service

@Service
class GreetingService {
    fun getGreeting(name: String): String {
        if (name.isBlank()) {
            throw IllegalArgumentException("Name can't be empty")
        }
        return "Hello $name"
    }
}