package com.nooomer.seum_leaderboards.controllers

import com.nooomer.seum_leaderboards.services.ScoreService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/score/")
class ScoreController(
    var scoreService: ScoreService
) {
@GetMapping("{leaderboardId}/{userId}")
    fun getScoreForUserAndLevel(@PathVariable leaderboardId: String, @PathVariable userId: String): ResponseEntity<Double> {
        return ResponseEntity.ok(scoreService.getScoreForUserByLevel(userId, leaderboardId))
    }
}