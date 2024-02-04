package com.nooomer.seum_leaderboards.services

import com.nooomer.seum_leaderboards.dto.Players
import com.nooomer.seum_leaderboards.dto.SteamProfileDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class SteamServise(
    var levelService: LevelService
) {
    private var playerSteamIds = mutableSetOf<Long>()
    @Value("\${app.steam.steamApiKey}")
    private lateinit var steamApiKey: String
    @Value("\${app.steam.steamMainApiLink}")
    private lateinit var steamMainApiLink: String
    @Value("\${app.steam.steamApiUserInfoLink}")
    private lateinit var steamApiUserInfoLink: String
    fun getPLayerInfoById(id: Long): ArrayList<Players>? {
        val restTemplate = RestTemplate()
        return restTemplate.getForEntity(
            steamMainApiLink + steamApiUserInfoLink + steamApiKey + "&steamids=${id}",
            SteamProfileDto::class.java
        ).body?.PLayerResponse?.players
    }

    fun getAllPlayers(){
        runBlocking { // this: CoroutineScope
            launch {
        levelService.getAllLevels().forEach { leaderboardDto ->
            levelService.getLevelLeaderboard(leaderboardDto.url)?.entries?.entry?.forEach {
                playerSteamIds.add(it.steamid.toLong())
            }
        }
                delay(2000)
                }

        }
    }
    //https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=KEY&steamids=76561197960435530
}