package com.nooomer.seum_leaderboards.db.repositories;

import com.nooomer.seum_leaderboards.db.models.Leaderboard
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface LeaderboardRepository : JpaRepository<Leaderboard, UUID> {
    fun findByLbId(lbId: Int):Leaderboard
}