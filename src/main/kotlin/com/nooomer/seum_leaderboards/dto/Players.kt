package com.nooomer.seum_leaderboards.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Players(
    @SerialName("steamid") var steamid: String? = null,
    @SerialName("communityvisibilitystate") var communityvisibilitystate: Int? = null,
    @SerialName("profilestate") var profilestate: Int? = null,
    @SerialName("personaname") var personaname: String? = null,
    @SerialName("profileurl") var profileurl: String? = null,
    @SerialName("avatar") var avatar: String? = null,
    @SerialName("avatarmedium") var avatarmedium: String? = null,
    @SerialName("avatarfull") var avatarfull: String? = null,
    @SerialName("avatarhash") var avatarhash: String? = null,
    @SerialName("personastate") var personastate: Int? = null,
    @SerialName("realname") var realname: String? = null,
    @SerialName("primaryclanid") var primaryclanid: String? = null,
    @SerialName("timecreated") var timecreated: Int? = null,
    @SerialName("personastateflags") var personastateflags: Int? = null,
    @SerialName("loccountrycode") var loccountrycode: String? = null,
    @SerialName("locstatecode") var locstatecode: String? = null,
    @SerialName("loccityid") var loccityid: Int? = null,
)