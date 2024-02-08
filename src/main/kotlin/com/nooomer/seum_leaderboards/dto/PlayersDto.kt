package com.nooomer.seum_leaderboards.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.Serial


/**
 * DTO for {@link com.nooomer.seum_leaderboards.db.models.Players}
 */
@Serializable
data class PlayersDto(
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
    @SerialName("lastlogoff") var lastlogoff: Int? = null,
    @SerialName("commentpermission") var commentpermission: Int? = null,
    @SerialName("gameid") var gameid: String? = null,
    @SerialName("gameserverip") var gameserverip: String? = null,
    @SerialName("gameextrainfo") var gameextrainfo: String? = null,
    @SerialName("cityid") var cityid: String? = null,
    @SerialName("gameserversteamid") var gameserversteamid: String? = null,
    @SerialName("lobbysteamid") var lobbysteamid: String? = null,

)