package com.nooomer.seum_leaderboards.services

import com.nooomer.Points
import org.springframework.stereotype.Service

@Service
class ScoreService(
    var levelService: LevelService,
) {

    fun getScoreForUserByLevel(userId: String, lbId: String): Double {
        val times: DoubleArray = DoubleArray(100)
        var playerTime: Double = 0.0
        var playerRank: Int = 0
        with(levelService.getLevelLeaderboard(levelService.getLevelDataById(lbId.toInt()).url)?.entries?.entry) {
            this?.forEachIndexed { index, value ->
                if(index<100) {
                    times[index] = value.score.toDouble()/1000
                }
            }
            with(this?.find {
                it.steamid == userId
            }) {
                playerRank = this?.rank?.toInt()!!
                playerTime = this.score.toDouble()/1000
            }
        }
       return Points.calcPoint(
            playerRank, playerTime, times
        )
    }
}