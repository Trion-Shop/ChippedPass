package com.megapari.chippedpass.data

import com.megapari.chippedpass.domain.model.*
import com.megapari.chippedpass.domain.repository.ChippedPassRepository
import kotlin.math.*

class InMemoryChippedPassRepository : ChippedPassRepository {

    override suspend fun getTrajectoryArchetypes(): List<ChippedTrajectoryArchetype> {
        return listOf(
            ChippedTrajectoryArchetype(
                id = "ct1",
                techniqueTitle = "The Totti Wedge Scoop",
                deliveryType = "Backline Clearance Scoop",
                loftAngleDegrees = 48.5f,
                backspinRpm = 1450,
                clearanceApexMeters = 3.6f,
                landingDeadWeightPct = 88.0f,
                biomechanicsBreakdown = "The kicking foot jabs sharply underneath the ball's lower circumference with a locked ankle and immediate arrested follow-through, generating massive backspin and parabolic lift.",
                executionPrinciples = listOf(
                    "Zero follow-through—foot stabs the turf beneath the ball",
                    "Leaning slightly back to enhance initial launch trajectory",
                    "Plant foot placed 20cm lateral to the ball center"
                ),
                defensiveCounters = listOf(
                    "Center-back backwards leap header",
                    "Goalkeeper stepping out to high penalty spot zone"
                )
            ),
            ChippedTrajectoryArchetype(
                id = "ct2",
                techniqueTitle = "The Pirlo Floated Dropper",
                deliveryType = "Backspin Drop into Deep Pocket",
                loftAngleDegrees = 42.0f,
                backspinRpm = 1680,
                clearanceApexMeters = 4.2f,
                landingDeadWeightPct = 94.5f,
                biomechanicsBreakdown = "Delicate instep strike with sliced downward angle, causing the ball to float serenely over a rigid defensive block and brake violently upon initial turf impact.",
                executionPrinciples = listOf(
                    "Brushing the lower-outer surface of the ball",
                    "High scanning rate to calculate runner's acceleration stride",
                    "Ball trajectory designed to hit turf exactly in runner's stride path"
                ),
                defensiveCounters = listOf(
                    "Recovery sliding intervention before second bounce",
                    "High offside trap step right on passer backlift"
                )
            ),
            ChippedTrajectoryArchetype(
                id = "ct3",
                techniqueTitle = "The Modrić Trivela Scoop",
                deliveryType = "Outside-Foot Curved Aerial Chip",
                loftAngleDegrees = 38.0f,
                backspinRpm = 1250,
                clearanceApexMeters = 2.9f,
                landingDeadWeightPct = 82.0f,
                biomechanicsBreakdown = "Struck using the outside three toes while shaping the hips away from the target, disguising the pass intention until the millisecond of contact.",
                executionPrinciples = listOf(
                    "Hips oriented toward near side to fool defending marker",
                    "Ankle snapped outward with upward flick finish",
                    "Natural outward-to-inward flight curvature bypassing head height"
                ),
                defensiveCounters = listOf(
                    "Anticipating the body shape tell-tale hip rotation",
                    "Tactical body obstruction preventing ankle snap"
                )
            ),
            ChippedTrajectoryArchetype(
                id = "ct4",
                techniqueTitle = "The Messi Dink Lob",
                deliveryType = "Short-Range Keeper/Block Elimination Dink",
                loftAngleDegrees = 54.0f,
                backspinRpm = 1800,
                clearanceApexMeters = 2.4f,
                landingDeadWeightPct = 96.0f,
                biomechanicsBreakdown = "Executed at high running speed from inside 16 meters, using the toe-cap wedge to gently elevate the ball just above diving limbs with pillow-soft cushion.",
                executionPrinciples = listOf(
                    "Minimal body preparation—strike looks like a ground shot",
                    "Ultra-short deceleration stride immediately before touch",
                    "Ball drops steeply with zero horizontal rollout"
                ),
                defensiveCounters = listOf(
                    "Goalkeeper staying on feet instead of going to ground",
                    "Back-tracking defender overhead goal-line clearance"
                )
            )
        )
    }

    override suspend fun getLoftDrills(): List<LoftAccuracyDrill> {
        return listOf(
            LoftAccuracyDrill(
                id = "ld1",
                drillTitle = "Barrel Drop Zone Target",
                trainingFocus = "Backspin Dead-Stop Calibration",
                targetDistanceMeters = 25,
                barrelLandingTargetRadiusMeters = 1.2f,
                targetSuccessRatePct = 75,
                drillProcedures = listOf(
                    "Position 3 high mannequins at 12 meters acting as a defensive wall",
                    "Target landing barrel placed at 25 meters",
                    "Ball must clear mannequins above head level and drop into the barrel"
                ),
                coachingPoints = listOf(
                    "Ensure maximum backspin to limit forward roll on miss",
                    "Keep head still through the exact moment of ball strike",
                    "Maintain stable plant knee flexion"
                )
            ),
            LoftAccuracyDrill(
                id = "ld2",
                drillTitle = "Human Wall Scoop to Overlap",
                trainingFocus = "Live Runner Timing & Elevation",
                targetDistanceMeters = 30,
                barrelLandingTargetRadiusMeters = 2.0f,
                targetSuccessRatePct = 80,
                drillProcedures = listOf(
                    "Passive 4-man defensive line holding the 18-meter line",
                    "Blindside winger makes diagonal burst behind line on visual cue",
                    "Passer chips ball over the center-back's shoulder into stride"
                ),
                coachingPoints = listOf(
                    "Synchronize kick release with runner's second acceleration stride",
                    "Trajectory apex must occur directly over the center-back's head",
                    "Flight duration must allow runner to finish without breaking stride"
                )
            ),
            LoftAccuracyDrill(
                id = "ld3",
                drillTitle = "Penalty Box Dink & Volley Wave",
                trainingFocus = "Short-Range Elevation Under Pressure",
                targetDistanceMeters = 16,
                barrelLandingTargetRadiusMeters = 1.5f,
                targetSuccessRatePct = 85,
                drillProcedures = listOf(
                    "Passer receives firm pass under closing defender pressure",
                    "One-touch setup, second touch chips over lunging block",
                    "Incoming striker volleys first-time into side netting"
                ),
                coachingPoints = listOf(
                    "Disguise the chip by maintaining upright posture",
                    "Soft ankle cushion on touch 1, locked wedge on touch 2",
                    "Elevation must clear 1.90m within first 4 meters of flight"
                )
            )
        )
    }

    override suspend fun getMatchClashes(): List<ChippedPassMatchClash> {
        return listOf(
            ChippedPassMatchClash(
                id = "mc1",
                headline = "The Wembley Aerial Masterclass",
                teamA = "Barcelona",
                teamB = "Manchester United",
                score = "3 - 1",
                passVirtuoso = "Lionel Messi & Andrés Iniesta",
                keyChippedAssistsCount = 2,
                defenseBypassedPlayersCount = 6,
                tacticalChronicle = "Guardiola's Barcelona unlocked Ferguson's rigid 4-4-2 low block by utilizing delicate chipped passes over Vidic and Ferdinand, feeding David Villa and Pedro in blind pockets.",
                decisiveLoftMoments = listOf(
                    DecisiveLoftMoment(
                        minute = 27,
                        passer = "Xavi Hernández",
                        receiver = "Pedro Rodríguez",
                        defendersOverhead = 2,
                        finishResult = "Deft ground finish inside near post after lofted scoop bypass"
                    ),
                    DecisiveLoftMoment(
                        minute = 69,
                        passer = "Lionel Messi",
                        receiver = "David Villa",
                        defendersOverhead = 3,
                        finishResult = "Sublime curler into the top corner following aerial breakdown"
                    )
                )
            ),
            ChippedPassMatchClash(
                id = "mc2",
                headline = "The Bernabéu Trivela Miracle",
                teamA = "Real Madrid",
                teamB = "Chelsea",
                score = "2 - 3 (Agg 5-4)",
                passVirtuoso = "Luka Modrić",
                keyChippedAssistsCount = 1,
                defenseBypassedPlayersCount = 4,
                tacticalChronicle = "Trailing 0-3 on the night, Modrić produced the most iconic chipped pass of modern football: a 30-meter outside-of-the-boot scoop dropping with pinpoint precision onto Rodrygo's volley.",
                decisiveLoftMoments = listOf(
                    DecisiveLoftMoment(
                        minute = 80,
                        passer = "Luka Modrić",
                        receiver = "Rodrygo",
                        defendersOverhead = 4,
                        finishResult = "First-time volley into corner saving Madrid's European campaign"
                    )
                )
            ),
            ChippedPassMatchClash(
                id = "mc3",
                headline = "The Pirlo Euro 2012 Symphony",
                teamA = "Italy",
                teamB = "England",
                score = "0 - 0 (4 - 2 pens)",
                passVirtuoso = "Andrea Pirlo",
                keyChippedAssistsCount = 3,
                defenseBypassedPlayersCount = 7,
                tacticalChronicle = "Pirlo systematically shredded England's deep defensive lines by delivering 8 pinpoint chipped scoops over John Terry and Lescott into the paths of Balotelli and De Rossi.",
                decisiveLoftMoments = listOf(
                    DecisiveLoftMoment(
                        minute = 25,
                        passer = "Andrea Pirlo",
                        receiver = "Mario Balotelli",
                        defendersOverhead = 2,
                        finishResult = "Balotelli lob attempt cleared off line by recovery tackle"
                    ),
                    DecisiveLoftMoment(
                        minute = 52,
                        passer = "Andrea Pirlo",
                        receiver = "Daniele De Rossi",
                        defendersOverhead = 3,
                        finishResult = "Uncontested volley narrowly sliding wide of post"
                    )
                )
            )
        )
    }

    override fun calculateChippedSimulation(
        loftAngle: Float,
        distanceMeters: Float,
        wallHeightMeters: Float
    ): ChippedSimulationResult {
        val angleRad = Math.toRadians(loftAngle.toDouble())

        // Calculate theoretical trajectory apex: H = (v0^2 * sin^2(theta)) / (2 * g)
        // Simplified ballistics approximation for 15-40m passes:
        val apex = (distanceMeters * 0.18f * sin(angleRad)).toFloat().coerceIn(1.8f, 6.5f)

        // Clearance margin over defensive wall placed at 40% of the trajectory distance
        val wallClearance = apex - wallHeightMeters

        // Landing softness increases with higher angle and distance
        val softness = ((loftAngle - 30f) * 2.2f + 40f).coerceIn(30f, 98f).toInt()

        // Goalkeeper risk increases if ball hangs too long in the air (high angle + long distance)
        val keeperRisk = ((apex * 10f) + (distanceMeters * 0.8f) - 15f).coerceIn(10f, 90f).toInt()

        val verdict = when {
            wallClearance < 0.2f ->
                "INTERCEPTION DANGER: Insufficient vertical lift. Defending center-back will head the ball clear at the trajectory crest."
            keeperRisk > 65 ->
                "GOALKEEPER CORRIDOR RISK: Trajectory apex is too high and hang-time too long. Sweeper-keeper has ample time to claim."
            softness > 80 && wallClearance > 0.8f ->
                "PERFECT CHIPPED FLIGHT: Clears defensive heads comfortably and drops with dead-weight cushion directly onto the runner's stride."
            else ->
                "VIABLE SCOOP TRAJECTORY: Bypasses defensive line with moderate margin. Runner must accelerate aggressively to beat recovery."
        }

        return ChippedSimulationResult(
            loftAngleDegrees = loftAngle,
            passDistanceMeters = distanceMeters,
            defensiveWallHeightMeters = wallHeightMeters,
            apexElevationMeters = String.format("%.2f", apex).toFloat(),
            wallClearanceMarginMeters = String.format("%.2f", wallClearance).toFloat(),
            landingSoftnessPct = softness,
            goalkeeperRushCatchRiskPct = keeperRisk,
            trajectoryVerdict = verdict
        )
    }
}
