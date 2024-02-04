package com.nooomer.seum_leaderboards.services.helpers

import com.nooomer.seum_leaderboards.db.models.Leaderboard
import com.nooomer.seum_leaderboards.dto.LeaderboardDto
import org.springframework.stereotype.Component

@Component
object Converter {
    fun LeaderboardDto.toLeaderboard(): Leaderboard {
        return Leaderboard(
            url, lbId, name, displayName, entries, sortMethod, displayType
        )
    }

    fun Leaderboard.toLeaderboardDto(): LeaderboardDto {
        return LeaderboardDto(
            url, lbId, name, displayName, entries, sortMethod, displayType
        )
    }

    fun List<LeaderboardDto>.toLeaderboardList(): MutableList<Leaderboard> {
        val newList = mutableListOf<Leaderboard>()
       return this.mapTo(newList) {
            it.toLeaderboard()
        }
    }

    fun MutableList<Leaderboard>.toLeaderboardDtoList(): MutableList<LeaderboardDto> {
        val newList = mutableListOf<LeaderboardDto>()
        return this.mapTo(newList) {
            it.toLeaderboardDto()
        }
    }
}