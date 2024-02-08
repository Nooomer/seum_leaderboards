package com.nooomer.seum_leaderboards.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerResponseDto(
    @SerialName("players")
    var players: ArrayList<PlayersDto> = arrayListOf()
)