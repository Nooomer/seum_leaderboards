package com.nooomer.seum_leaderboards.services

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.nooomer.seum_leaderboards.db.repositories.LeaderboardRepository
import com.nooomer.seum_leaderboards.dto.LbResponse
import com.nooomer.seum_leaderboards.dto.LeaderboardDto
import com.nooomer.seum_leaderboards.dto.LevelLeaderboardsResponse
import com.nooomer.seum_leaderboards.services.helpers.Converter.toLeaderboardDto
import com.nooomer.seum_leaderboards.services.helpers.Converter.toLeaderboardDtoList
import com.nooomer.seum_leaderboards.services.helpers.Converter.toLeaderboardList
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class LevelService(
    var leaderboardRepository: LeaderboardRepository,
) {
    @Value("\${app.steam.steamLeaderboardsLink}")
    private lateinit var steamLeaderboardsLink: String

    @Value("\${app.steam.steamLeaderboardsLinkXmlPostfix}")
    private lateinit var steamLeaderboardsLinkXmlPostfix: String

    fun getAllLevels(): MutableList<LeaderboardDto> {
        return leaderboardRepository.findAll().toLeaderboardDtoList()
    }

    fun getLevelDataById(lbId: Int): LeaderboardDto {
        return leaderboardRepository.findByLbId(lbId).toLeaderboardDto()
    }

    fun getLevelLeaderboard(url: String): LevelLeaderboardsResponse? {
        val module = JacksonXmlModule()
        module.setDefaultUseWrapper(false)
        val xmlMapper = XmlMapper(module)
        val xmlString = requestLevelLeaderboards(url)
        return if(xmlString != ""){
            xmlMapper.readValue(requestLevelLeaderboards(url), LevelLeaderboardsResponse::class.java)
        }
        else{
            null
        }
    }
    private fun requestLevelLeaderboards(url: String): String? {
        val restTemplate = RestTemplate()
        return with(restTemplate.getForEntity(
            "$url&end=200",
            String::class.java
        )){
            if(statusCode.isError){
                ""
            }
            else{
                body
            }
        }
    }

    private fun getXmlAsString(): String? {
        val restTemplate = RestTemplate()
        return restTemplate.getForEntity(
            steamLeaderboardsLink + steamLeaderboardsLinkXmlPostfix,
            String::class.java
        ).body
    }

    private fun convertXmlToDto(xml: String?): LbResponse? {
        val module = JacksonXmlModule()
        module.setDefaultUseWrapper(false)
        val xmlMapper = XmlMapper(module)
        return xmlMapper.readValue(xml, LbResponse::class.java)
    }

    private fun parseLevelFromSteamXml(): LbResponse? {
        return convertXmlToDto(getXmlAsString())
    }

    fun updateLevels() {
        val leaderboards = parseLevelFromSteamXml()?.leaderboardDto?.toLeaderboardList()
        leaderboardRepository.saveAll(leaderboards!!)
    }

}