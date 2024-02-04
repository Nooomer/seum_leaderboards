package com.nooomer.seum_leaderboards.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerResponse(
    @SerialName("players")
    var players: ArrayList<Players> = arrayListOf()
)