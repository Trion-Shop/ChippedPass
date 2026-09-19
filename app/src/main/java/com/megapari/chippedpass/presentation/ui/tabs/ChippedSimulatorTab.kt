package com.megapari.chippedpass.presentation.ui.tabs

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.megapari.chippedpass.core.theme.*
import com.megapari.chippedpass.presentation.ui.components.ChipMetricBadge
import com.megapari.chippedpass.presentation.viewmodel.ChippedUiState
import kotlin.math.*

@Composable
fun ChippedSimulatorTab(
    uiState: ChippedUiState,
    onUpdateSim: (Float, Float, Float) -> Unit,
    modifier: Modifier = Modifier
) {
    val sim = uiState.simulationResult

    val infiniteTransition = rememberInfiniteTransition(label = "flight")
    val flightProgress by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "flightProgress"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "PARABOLIC CHIP TRAJECTORY SIMULATOR",
            color = ChipBlue,
            fontSize = 16.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 0.8.sp
        )
        Text(
            text = "Model launch loft angle, apex elevation, defensive wall clearance, and soft landing.",
            color = ChipMutedSky,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Visual Parabolic Canvas Pitch Simulation
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = ChipDarkBg),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ChipBorder)),
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val w = size.width
                    val h = size.height

                    val groundY = h * 0.82f
                    val startX = 35.dp.toPx()
                    val targetX = w - 45.dp.toPx()

                    // Draw Turf Ground Line
                    drawLine(
                        color = ChipBorder,
                        start = Offset(16.dp.toPx(), groundY),
                        end = Offset(w - 16.dp.toPx(), groundY),
                        strokeWidth = 2.dp.toPx()
                    )

                    // Defensive Wall Mannequins at 40% of pass distance
                    val wallX = startX + 0.42f * (targetX - startX)
                    val wallHeightPx = (uiState.simWallHeightMeters / 6.0f) * (groundY * 0.7f)
                    val wallTopY = groundY - wallHeightPx

                    // Draw Wall bar
                    drawRect(
                        color = ChipRed.copy(alpha = 0.6f),
                        topLeft = Offset(wallX - 8.dp.toPx(), wallTopY),
                        size = Size(16.dp.toPx(), wallHeightPx)
                    )
                    drawRect(
                        color = ChipRedFlame,
                        topLeft = Offset(wallX - 8.dp.toPx(), wallTopY),
                        size = Size(16.dp.toPx(), wallHeightPx),
                        style = Stroke(width = 1.dp.toPx())
                    )

                    // Parabolic curve apex
                    val apexFraction = ((sim?.apexElevationMeters ?: 3.0f) / 6.5f).coerceIn(0.2f, 0.9f)
                    val apexY = groundY - (apexFraction * (groundY * 0.85f))
                    val apexX = startX + 0.45f * (targetX - startX)

                    // Generate parabola points using Bezier quadratic curve
                    val flightPath = Path().apply {
                        moveTo(startX, groundY)
                        quadraticTo(apexX, apexY - (groundY - apexY), targetX, groundY)
                    }

                    // Draw trajectory arc guide
                    drawPath(
                        path = flightPath,
                        color = ChipBlueBright.copy(alpha = 0.7f),
                        style = Stroke(
                            width = 2.dp.toPx(),
                            pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(8f, 8f))
                        )
                    )

                    // Animated Flying Ball on Parabola
                    val t = flightProgress
                    // Quadratic Bezier: B(t) = (1-t)^2*P0 + 2(1-t)t*P1 + t^2*P2
                    val controlY = apexY - (groundY - apexY)
                    val ballCurrentX = (1 - t) * (1 - t) * startX + 2 * (1 - t) * t * apexX + t * t * targetX
                    val ballCurrentY = (1 - t) * (1 - t) * groundY + 2 * (1 - t) * t * controlY + t * t * groundY

                    // Draw glowing flight ball
                    drawCircle(
                        color = ChipBlueBright,
                        center = Offset(ballCurrentX, ballCurrentY),
                        radius = 8.dp.toPx()
                    )
                    drawCircle(
                        color = ChipWhite,
                        center = Offset(ballCurrentX, ballCurrentY),
                        radius = 4.dp.toPx()
                    )

                    // Draw Passer at start
                    drawCircle(
                        color = ChipBlue,
                        center = Offset(startX, groundY),
                        radius = 10.dp.toPx()
                    )

                    // Draw Target Receiver at finish
                    drawCircle(
                        color = ChipBlueBright,
                        center = Offset(targetX, groundY),
                        radius = 10.dp.toPx()
                    )
                    drawCircle(
                        color = ChipDarkBg,
                        center = Offset(targetX, groundY),
                        radius = 5.dp.toPx()
                    )
                }

                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "● Passer/Receiver (Blue)",
                        color = ChipBlueBright,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "■ Defensive Wall (Red)",
                        color = ChipRedFlame,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sliders & Controls
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = ChipDarkCard),
            border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ChipBorder)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Launch Loft Angle",
                        color = ChipWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${uiState.simLoftAngle.toInt()}°",
                        color = ChipBlueBright,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Slider(
                    value = uiState.simLoftAngle,
                    onValueChange = { onUpdateSim(it, uiState.simDistanceMeters, uiState.simWallHeightMeters) },
                    valueRange = 32f..62f,
                    colors = SliderDefaults.colors(
                        thumbColor = ChipBlueBright,
                        activeTrackColor = ChipBlueBright,
                        inactiveTrackColor = ChipDarkElevated
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Target Pass Distance (Meters)",
                        color = ChipWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${uiState.simDistanceMeters.toInt()} m",
                        color = ChipBlueBright,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Slider(
                    value = uiState.simDistanceMeters,
                    onValueChange = { onUpdateSim(uiState.simLoftAngle, it, uiState.simWallHeightMeters) },
                    valueRange = 15f..45f,
                    colors = SliderDefaults.colors(
                        thumbColor = ChipBlue,
                        activeTrackColor = ChipBlue,
                        inactiveTrackColor = ChipDarkElevated
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Defensive Wall Height (Meters)",
                        color = ChipWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = String.format("%.2f m", uiState.simWallHeightMeters),
                        color = ChipRedFlame,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                Slider(
                    value = uiState.simWallHeightMeters,
                    onValueChange = { onUpdateSim(uiState.simLoftAngle, uiState.simDistanceMeters, it) },
                    valueRange = 1.70f..2.30f,
                    colors = SliderDefaults.colors(
                        thumbColor = ChipRedFlame,
                        activeTrackColor = ChipRedFlame,
                        inactiveTrackColor = ChipDarkElevated
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Simulation Output
        if (sim != null) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = ChipDarkElevated),
                border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(ChipBlueBright)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "TRAJECTORY BALLISTICS OUTPUT",
                        color = ChipBlueBright,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ChipMetricBadge(
                            label = "Apex Lift",
                            value = "${sim.apexElevationMeters}m",
                            modifier = Modifier.weight(1f)
                        )
                        ChipMetricBadge(
                            label = "Wall Clearance",
                            value = "${sim.wallClearanceMarginMeters}m",
                            modifier = Modifier.weight(1f)
                        )
                        ChipMetricBadge(
                            label = "Keeper Risk",
                            value = "${sim.goalkeeperRushCatchRiskPct}%",
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = sim.trajectoryVerdict,
                        color = ChipSoftSilver,
                        fontSize = 12.sp,
                        lineHeight = 17.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}
