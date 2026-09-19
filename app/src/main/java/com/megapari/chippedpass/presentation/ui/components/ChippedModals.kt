package com.megapari.chippedpass.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.megapari.chippedpass.core.theme.*
import com.megapari.chippedpass.domain.model.*

@Composable
fun TrajectoryDetailModal(
    archetype: ChippedTrajectoryArchetype,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = ChipDarkBg),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ChipBlueBright)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = archetype.techniqueTitle.uppercase(),
                    color = ChipBlue,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = archetype.deliveryType,
                    color = ChipRedFlame,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ChipMetricBadge(
                        label = "Clearance Apex",
                        value = "${archetype.clearanceApexMeters}m",
                        modifier = Modifier.weight(1f)
                    )
                    ChipMetricBadge(
                        label = "Backspin",
                        value = "${archetype.backspinRpm}",
                        modifier = Modifier.weight(1f)
                    )
                    ChipMetricBadge(
                        label = "Soft Cushion",
                        value = "${archetype.landingDeadWeightPct.toInt()}%",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Biomechanical Execution",
                    color = ChipWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = archetype.biomechanicsBreakdown,
                    color = ChipSoftSilver,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Striking Principles",
                    color = ChipBlueBright,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                archetype.executionPrinciples.forEach { principle ->
                    Text(
                        text = "• $principle",
                        color = ChipMutedSky,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "Defensive Counter-Measures",
                    color = ChipRedFlame,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                archetype.defensiveCounters.forEach { counter ->
                    Text(
                        text = "• $counter",
                        color = ChipSoftSilver,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = ChipDarkElevated),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("CLOSE", color = ChipBlueBright, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun LoftDrillDetailModal(
    drill: LoftAccuracyDrill,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = ChipDarkBg),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ChipBlue)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = drill.drillTitle.uppercase(),
                    color = ChipBlueBright,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = drill.trainingFocus,
                    color = ChipMutedSky,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ChipMetricBadge(
                        label = "Distance",
                        value = "${drill.targetDistanceMeters}m",
                        modifier = Modifier.weight(1f)
                    )
                    ChipMetricBadge(
                        label = "Drop Target",
                        value = "${drill.barrelLandingTargetRadiusMeters}m",
                        modifier = Modifier.weight(1f)
                    )
                    ChipMetricBadge(
                        label = "Target Goal",
                        value = "${drill.targetSuccessRatePct}%",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Drill Setup & Procedures",
                    color = ChipWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                drill.drillProcedures.forEach { proc ->
                    Text(
                        text = "• $proc",
                        color = ChipSoftSilver,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "Key Coaching Points",
                    color = ChipRedFlame,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                drill.coachingPoints.forEachIndexed { idx, point ->
                    Text(
                        text = "${idx + 1}. $point",
                        color = ChipMutedSky,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = ChipDarkElevated),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("CLOSE", color = ChipBlueBright, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ChippedMatchDetailModal(
    clash: ChippedPassMatchClash,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = ChipDarkBg),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ChipBorder)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = clash.headline.uppercase(),
                        color = ChipBlue,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = clash.score,
                        color = ChipWhite,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${clash.teamA} vs ${clash.teamB}",
                    color = ChipRedFlame,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ChipMetricBadge(
                        label = "Chipped Assists",
                        value = "${clash.keyChippedAssistsCount}",
                        modifier = Modifier.weight(1f)
                    )
                    ChipMetricBadge(
                        label = "Defenders Beaten",
                        value = "${clash.defenseBypassedPlayersCount}",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Tactical Chronicle",
                    color = ChipWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = clash.tacticalChronicle,
                    color = ChipSoftSilver,
                    fontSize = 12.sp,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Decisive Aerial Sequences",
                    color = ChipBlueBright,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                clash.decisiveLoftMoments.forEach { moment ->
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = ChipDarkElevated),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${moment.minute}' | ${moment.passer}",
                                    color = ChipBlue,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "${moment.defendersOverhead} DEF OVERHEAD",
                                    color = ChipRedFlame,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.ExtraBold
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Receiver: ${moment.receiver}",
                                color = ChipWhite,
                                fontSize = 11.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Finish: ${moment.finishResult}",
                                color = ChipMutedSky,
                                fontSize = 11.sp,
                                lineHeight = 14.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = ChipDarkElevated),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("CLOSE", color = ChipBlueBright, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
