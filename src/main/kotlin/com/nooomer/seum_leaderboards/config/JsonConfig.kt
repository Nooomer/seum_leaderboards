package com.nooomer.seum_leaderboards.config

import kotlinx.serialization.json.Json
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JsonConfig {
    @Bean
    fun setSerializableParams(): Json {
        return Json {
            ignoreUnknownKeys = true
        }
    }
}