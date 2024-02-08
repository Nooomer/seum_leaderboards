package com.nooomer.seum_leaderboards.controllers

import com.nooomer.seum_leaderboards.dto.PlayersDto
import com.nooomer.seum_leaderboards.services.SteamServise
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/steam")
class SteamProfileController(
    var steamServise: SteamServise
) {
    @GetMapping("/getProfile/{id}")
    fun getProfile(@PathVariable id: String): ResponseEntity<ArrayList<PlayersDto>> {
        return ResponseEntity.ok(steamServise.getPLayerInfoById(id.toLong()))
    }

    @GetMapping("/getAllPLayers")
    fun getAllPlayers(): ResponseEntity<Unit> {
        return ResponseEntity.ok(steamServise.getAllPlayers())
    }
    @GetMapping("/updateInfo")
    fun updateUserInfo(): ResponseEntity<Unit> {
        return ResponseEntity.ok(steamServise.getSteamUserInfo())
    }
}