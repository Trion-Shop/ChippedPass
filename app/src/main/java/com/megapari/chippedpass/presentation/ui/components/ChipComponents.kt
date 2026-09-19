package com.megapari.chippedpass.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.megapari.chippedpass.core.theme.*
import com.megapari.chippedpass.domain.model.*

@Composable
fun ChipHeader(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = ChipDarkCard,
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            )
            .border(
                width = 1.dp,
                color = ChipBorder,
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            )
            .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = title.uppercase(),
                    color = ChipBlue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.2.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    color = ChipMutedSky,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(ChipDarkElevated)
                    .border(1.dp, ChipRed, RoundedCornerShape(8.dp))
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(
                    text = "AERIAL SCOOP",
                    color = ChipRedFlame,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}

@Composable
fun ChipMetricBadge(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(ChipDarkBg.copy(alpha = 0.85f))
            .border(1.dp, ChipBorder, RoundedCornerShape(10.dp))
            .padding(horizontal = 10.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            color = ChipBlueBright,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label.uppercase(),
            color = ChipMutedSky,
            fontSize = 9.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun TrajectoryArchetypeCard(
    archetype: ChippedTrajectoryArchetype,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = ChipDarkCard),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ChipBorder))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = archetype.techniqueTitle,
                    color = ChipWhite,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(ChipDarkElevated)
                        .border(1.dp, ChipRed, RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "${archetype.loftAngleDegrees.toInt()}° LOFTER",
                        color = ChipRedFlame,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = archetype.deliveryType,
                color = ChipBlue,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = archetype.biomechanicsBreakdown,
                color = ChipSoftSilver,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ChipMetricBadge(
                    label = "Apex Lift",
                    value = "${archetype.clearanceApexMeters}m",
                    modifier = Modifier.weight(1f)
                )
                ChipMetricBadge(
                    label = "Backspin",
                    value = "${archetype.backspinRpm} RPM",
                    modifier = Modifier.weight(1f)
                )
                ChipMetricBadge(
                    label = "Soft Landing",
                    value = "${archetype.landingDeadWeightPct.toInt()}%",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun LoftDrillCard(
    drill: LoftAccuracyDrill,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = ChipDarkCard),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ChipBorder))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = drill.drillTitle,
                    color = ChipWhite,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(ChipBlue.copy(alpha = 0.2f))
                        .border(1.dp, ChipBlue, RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = "${drill.targetDistanceMeters}m TARGET",
                        color = ChipBlueBright,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = drill.trainingFocus,
                color = ChipMutedSky,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ChipMetricBadge(
                    label = "Barrel Radius",
                    value = "${drill.barrelLandingTargetRadiusMeters}m",
                    modifier = Modifier.weight(1f)
                )
                ChipMetricBadge(
                    label = "Success Goal",
                    value = "${drill.targetSuccessRatePct}%",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun ChippedMatchCard(
    clash: ChippedPassMatchClash,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = ChipDarkCard),
        border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ChipBlue.copy(alpha = 0.6f)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = clash.headline,
                    color = ChipBlueBright,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Black
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(ChipDarkBg)
                        .border(1.dp, ChipRed, RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = clash.score,
                        color = ChipWhite,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "${clash.teamA} vs ${clash.teamB}",
                color = ChipWhite,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Virtuoso: ${clash.passVirtuoso}",
                color = ChipRedFlame,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = clash.tacticalChronicle,
                color = ChipSoftSilver,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ChipMetricBadge(
                    label = "Key Assists",
                    value = "${clash.keyChippedAssistsCount}",
                    modifier = Modifier.weight(1f)
                )
                ChipMetricBadge(
                    label = "Bypassed Players",
                    value = "${clash.defenseBypassedPlayersCount}",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
