package com.nooomer.seum_leaderboards.controllers

import com.nooomer.seum_leaderboards.dto.LeaderboardDto
import com.nooomer.seum_leaderboards.services.LevelService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/level")
class LevelController(
    var levelService: LevelService,
) {
    @GetMapping("/all")
    fun getAllLevels(): ResponseEntity<MutableList<LeaderboardDto>> {
        return ResponseEntity.ok(levelService.getAllLevels())
    }

    @GetMapping("/update")
    fun updatedLevelsData(): ResponseEntity<Any> {
        levelService.updateLevels()
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{id}")
    fun getLevelDataById(@PathVariable id: String): ResponseEntity<LeaderboardDto> {
        return ResponseEntity.ok(levelService.getLevelDataById(id.toInt()))
    }

    
}