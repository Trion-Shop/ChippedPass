package com.megapari.chippedpass.domain.model

data class ChippedTrajectoryArchetype(
    val id: String,
    val techniqueTitle: String,
    val deliveryType: String, // Wedge Scoop Over Backline, Backspin Drop into Pocket, Angled Trivela Chip, Blindside Lob to Runner
    val loftAngleDegrees: Float,
    val backspinRpm: Int,
    val clearanceApexMeters: Float,
    val landingDeadWeightPct: Float, // Soft bounce landing percentage
    val biomechanicsBreakdown: String,
    val executionPrinciples: List<String>,
    val defensiveCounters: List<String>
)

data class LoftAccuracyDrill(
    val id: String,
    val drillTitle: String,
    val trainingFocus: String, // Mini-Goal Drop Zone, Human Mannequin Wall Scoop, Over-the-Top Timing Loop
    val targetDistanceMeters: Int,
    val barrelLandingTargetRadiusMeters: Float,
    val targetSuccessRatePct: Int,
    val drillProcedures: List<String>,
    val coachingPoints: List<String>
)

data class ChippedPassMatchClash(
    val id: String,
    val headline: String,
    val teamA: String,
    val teamB: String,
    val score: String,
    val passVirtuoso: String,
    val keyChippedAssistsCount: Int,
    val defenseBypassedPlayersCount: Int,
    val tacticalChronicle: String,
    val decisiveLoftMoments: List<DecisiveLoftMoment>
)

data class DecisiveLoftMoment(
    val minute: Int,
    val passer: String,
    val receiver: String,
    val defendersOverhead: Int,
    val finishResult: String
)

data class ChippedSimulationResult(
    val loftAngleDegrees: Float,
    val passDistanceMeters: Float,
    val defensiveWallHeightMeters: Float,
    val apexElevationMeters: Float,
    val wallClearanceMarginMeters: Float,
    val landingSoftnessPct: Int,
    val goalkeeperRushCatchRiskPct: Int,
    val trajectoryVerdict: String
)
