package com.nooomer

import org.apache.commons.math3.special.Erf
import org.apache.commons.math3.special.Erf.erf
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation
import kotlin.math.floor
import kotlin.math.ln
import kotlin.math.pow

object Points {
    private var curvature: Double = 1.48
    private var weight: Double = 5.5
    private var deviation: Double = 1.22
    private var scaling: Double = 0.38
    private var decay: Double = 0.32
    private var baselineWeight: Double = 0.4
    private var standardDeviation: Double = 0.0
    private var standardisedVolatility: Double = 0.0
    private var transformedDeviation: Double = 0.0
    private var rankAdjustment: Double = 0.0
    private var dynamicRankWeight: Double = 0.0
    private var weightedDeviation: Double = 0.0
    fun calcPoint(
        rank1Time: Float,
        playerTime: Float,
        rank10Time: Float,
        playerRank: Int,
    ): Int {
        return (((((((rank1Time / playerTime) / (1.007 - (rank1Time / playerTime)) * (rank10Time / rank1Time)) / 1.4425 / 1.75) + (100 / (playerRank / 1.375) * 1.8)) * 50))).toInt()
    }

    fun calcPoint(playerRank: Int, playerTime: Double, times: DoubleArray): Double {
        standardDeviation = calcStandartDeviationOftimes(times)
        standardisedVolatility = calcStandardisedVolatility(times)
        transformedDeviation = calcTransformedTimeDeviation(playerTime, times.min())
        rankAdjustment = calcRankAdjustment(playerRank)
        dynamicRankWeight = calcDynamicRankWeight()
        weightedDeviation = calcWeightedDeviation()
        return floor(10000 * (1 - (0.5 + 0.5 * erf(ln(weightedDeviation)/ curvature))))
    }

    private fun calcStandartDeviationOftimes(times: DoubleArray): Double {
        val sd = StandardDeviation(true)
        return sd.evaluate(times)
    }

    private fun calcStandardisedVolatility(times: DoubleArray): Double {
        return standardDeviation / times.min()
    }

    private fun calcTransformedTimeDeviation(playerTime: Double, topTime: Double): Double {
       return deviation * ((playerTime - topTime) / standardDeviation)
    }

    private fun calcRankAdjustment(playerRank: Int): Double {
        return scaling *  ((playerRank - 1).toDouble().pow(decay) )
    }

    private fun calcDynamicRankWeight(): Double {
        return 1-(standardisedVolatility + (1 - baselineWeight).pow(-1/weight)).pow(-weight)
    }

    private fun calcWeightedDeviation(): Double {
        return (dynamicRankWeight * rankAdjustment) + (1 - dynamicRankWeight) * transformedDeviation
    }

}