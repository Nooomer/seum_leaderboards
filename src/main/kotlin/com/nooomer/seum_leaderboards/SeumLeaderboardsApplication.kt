package com.nooomer.seum_leaderboards

import kotlinx.serialization.json.Json
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SeumLeaderboardsApplication

fun main(args: Array<String>) {
    runApplication<SeumLeaderboardsApplication>(*args)
}
