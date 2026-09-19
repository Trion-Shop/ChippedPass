package com.megapari.chippedpass.domain.repository

import com.megapari.chippedpass.domain.model.*

interface ChippedPassRepository {
    suspend fun getTrajectoryArchetypes(): List<ChippedTrajectoryArchetype>
    suspend fun getLoftDrills(): List<LoftAccuracyDrill>
    suspend fun getMatchClashes(): List<ChippedPassMatchClash>
    fun calculateChippedSimulation(
        loftAngle: Float,
        distanceMeters: Float,
        wallHeightMeters: Float
    ): ChippedSimulationResult
}
