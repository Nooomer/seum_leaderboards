package com.nooomer.seum_leaderboards.dto

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable


/*<response>
<appID>457210</appID>
<appFriendlyName>457210</appFriendlyName>
<leaderboardID>4906378</leaderboardID>
<totalLeaderboardEntries>23125</totalLeaderboardEntries>
<entryStart>0</entryStart>
<entryEnd>5000</entryEnd>
<nextRequestURL>
<![CDATA[ https://steamcommunity.com/stats/457210/leaderboards/4906378/?xml=1&start=5001 ]]>
</nextRequestURL>
<resultCount>5000</resultCount>
<entries>
<entry>
<steamid>76561199058697076</steamid>
<score>574999</score>
<rank>1</rank>
<ugcid>18446744073709551615</ugcid>
<details>
<![CDATA[ ]]>
</details>*/
@Serializable
data class LevelLeaderboardsResponse (
    @JsonProperty("appID")
    val appID: String,
    @JsonProperty("appFriendlyName")
    val appFriendlyName: String,
    @JsonProperty("leaderboardID")
    val leaderboardID: String,
    @JsonProperty("totalLeaderboardEntries")
    val totalLeaderboardEntries: String,
    @JsonProperty("entryStart")
    val entryStart: String,
    @JsonProperty("entryEnd")
    val entryEnd: String,
    @JsonProperty("nextRequestURL")
    val nextRequestURL: String? = null,
    @JsonProperty("resultCount")
    val resultCount: String,
    @JsonProperty("entries")
    val entries: Entries
)
@Serializable
data class Entries (
    @JsonProperty("entry")
    val entry: List<Entry>
)
@Serializable
data class Entry (
    @JsonProperty("steamid")
    val steamid: String,
    @JsonProperty("score")
    val score: String,
    @JsonProperty("rank")
    val rank: String,
    @JsonProperty("ugcid")
    val ugcid: String,
    @JsonProperty("details")
    val details: String
)
