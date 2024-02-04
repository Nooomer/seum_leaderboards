package com.nooomer.seum_leaderboards.dto

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * DTO for {@link com.nooomer.seum_leaderboards.db.models.LeaderboardEntity}
 */

@Serializable
data class LbResponse(
    @JsonProperty("appID")
    var appId: Int,
    @JsonProperty("appFriendlyName")
    var appFriendlyName: Int,
    @JsonProperty("leaderboardCount")
    var leaderboardCount: Int,
    @JsonProperty("leaderboard")
    var leaderboardDto: List<LeaderboardDto>,
)

@Serializable
data class LeaderboardDto(
    @JsonProperty("url")
    var url: String,
    @JsonProperty("lbid")
    var lbId: Int,
    @JsonProperty("name")
    var name: String,
    @JsonProperty("display_name")
    @SerialName("display_name")
    var displayName: String,
    @JsonProperty("entries")
    var entries: Int,
    @SerialName("sortmethod")
    @JsonProperty("sortmethod")
    var sortMethod: Int,
    @JsonProperty("displaytype")
    @SerialName("displaytype")
    var displayType: Int,
)

/*Structure example:
<url>https://steamcommunity.com/stats/457210/leaderboards/4906378/?xml=1</url>
<lbid>4906378</lbid>
<name>endless0_v8</name>
<display_name>Endless Mode</display_name>
<entries>23123</entries>
<sortmethod>2</sortmethod>
<displaytype>1</displaytype>*/
