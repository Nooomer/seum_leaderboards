package com.nooomer.seum_leaderboards.db.repositories;

import com.nooomer.seum_leaderboards.db.models.Player
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PlayerRepository : JpaRepository<Player, UUID> {
    fun findByUpdatedDateIsNullOrderBySteamid(pageable: Pageable): Page<Player>
}