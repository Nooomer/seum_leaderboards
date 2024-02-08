package com.nooomer.seum_leaderboards.services

import com.nooomer.Points
import com.nooomer.seum_leaderboards.db.models.Player
import com.nooomer.seum_leaderboards.db.repositories.PlayerRepository
import com.nooomer.seum_leaderboards.dto.PlayersDto
import com.nooomer.seum_leaderboards.dto.SteamProfileDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class SteamServise(
    var levelService: LevelService,
    var playerRepository: PlayerRepository,
) {
    private fun <T> CoroutineScope.asyncIO(ioFun: () -> T) = async { ioFun() }
    private var playerSteamIds = mutableSetOf<Long>()

    @Value("\${app.steam.steamApiKey}")
    private lateinit var steamApiKey: String

    @Value("\${app.steam.steamMainApiLink}")
    private lateinit var steamMainApiLink: String

    @Value("\${app.steam.steamApiUserInfoLink}")
    private lateinit var steamApiUserInfoLink: String
    fun getPLayerInfoById(id: Long): ArrayList<PlayersDto>? {
        val restTemplate = RestTemplate()
        return restTemplate.getForEntity(
            steamMainApiLink + steamApiUserInfoLink + steamApiKey + "&steamids=${id}",
            SteamProfileDto::class.java
        ).body?.PLayerResponseDto?.players
    }

    fun MutableSet<Long>.toList(): List<Player> {
        return this.map {
            Player(
                steamid = it.toString()
            )
        }
    }

    fun MutableList<Player>.toMutableIdsSet(): MutableSet<Long> {
        return this.map {
            it.steamid!!.toLong()
        }.toMutableSet()
    }

    fun getLbAsync() {
        playerSteamIds = playerRepository.findAll().toMutableIdsSet()
        levelService.getAllLevels().forEach { leaderboardDto ->
            with(levelService.getLevelLeaderboard(leaderboardDto.url)) {
                if (this == null) {
                    val playerIdsList = playerSteamIds.map {
                        Player(
                            steamid = it.toString()
                        )
                    }
                    playerRepository.saveAll(playerIdsList)
                } else {
                    entries.entry.forEach {
                        playerSteamIds.add(it.steamid.toLong())
                    }
                    playerRepository.saveAll(playerSteamIds.toList())
                }
            }

        }
    }

    fun getAllPlayers() {
        //getLbAsync()
        fixDuplicate()
    }

    fun fixDuplicate() {
        playerSteamIds = playerRepository.findAll().toMutableIdsSet()
        playerRepository.deleteAll()
        playerRepository.saveAll(playerSteamIds.toList())
    }

    fun requestProfileData(ids: List<Player>): ArrayList<PlayersDto>? {
        val temp = mutableListOf<String>()
        ids.forEach {
            temp.add(it.steamid.toString())
        }
        val restTemplate = RestTemplate()
        return restTemplate.getForEntity(
            steamMainApiLink + steamApiUserInfoLink + steamApiKey + "&steamids=${temp}",
            SteamProfileDto::class.java
        ).body?.PLayerResponseDto?.players
    }

    fun processAndSaveProfiles(profiles: ArrayList<PlayersDto>?, dbProfiles: List<Player>) {
        val profilesWithId = profiles.toPlayer()
        profilesWithId?.forEach {
            it.id = dbProfiles.find { dbPlayer ->
                dbPlayer.steamid == it.steamid
            }?.id
        }
        playerRepository.saveAll(profilesWithId!!.toMutableList())
    }

    fun PlayersDto.toPlayer(): Player {
        return Player(
            steamid,
            communityvisibilitystate,
            profilestate,
            personaname,
            profileurl,
            avatar,
            avatarmedium,
            avatarfull,
            avatarhash,
            personastate,
            realname,
            primaryclanid,
            timecreated,
            personastateflags,
            loccountrycode,
            locstatecode,
            loccityid
        )
    }

    fun ArrayList<PlayersDto>?.toPlayer(): List<Player>? {
        return this?.map {
            it.toPlayer()
        }
    }

    fun getSteamUserInfo() {
        var pageNumber = 0
        val page = Pageable.ofSize(100)
        var pages = playerRepository.findByUpdatedDateIsNullOrderBySteamid(page)
        while (pages.hasNext()) {
            val playerInfo = requestProfileData(pages.content)
            processAndSaveProfiles(playerInfo, pages.content)
            pages = playerRepository.findByUpdatedDateIsNullOrderBySteamid(page.withPage(pageNumber++))
            //pages = playerRepository.findAllByOrderBySteamid(page)
        }
        val playerInfo = requestProfileData(pages.content)
        processAndSaveProfiles(playerInfo, pages.content)
        pages = playerRepository.findByUpdatedDateIsNullOrderBySteamid(page.withPage(pageNumber++))
    }
    //https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=KEY&steamids=76561197960435530
}