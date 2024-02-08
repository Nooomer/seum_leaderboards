package com.nooomer.seum_leaderboards.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SteamProfileDto (
  @SerialName("response" ) var PLayerResponseDto : PlayerResponseDto? = PlayerResponseDto()
)