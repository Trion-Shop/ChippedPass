package com.megapari.chippedpass.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChangeHistory
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Paragliding
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.megapari.chippedpass.core.theme.*
import com.megapari.chippedpass.presentation.ui.components.*
import com.megapari.chippedpass.presentation.ui.tabs.*
import com.megapari.chippedpass.presentation.viewmodel.ChippedViewModel

data class ChippedNavItem(
    val title: String,
    val icon: ImageVector
)

@Composable
fun ChippedPassScreen(
    viewModel: ChippedViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    val navItems = listOf(
        ChippedNavItem("Derbies", Icons.Default.SportsSoccer),
        ChippedNavItem("Scoop Sim", Icons.Default.Paragliding),
        ChippedNavItem("Lofts", Icons.Default.ChangeHistory),
        ChippedNavItem("Drills", Icons.Default.FitnessCenter)
    )

    Scaffold(
        bottomBar = {
            // Floating Curved Arc Bottom Navigation with cyan pill active indicator
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .padding(start = 16.dp, end = 16.dp, bottom = 10.dp),
                shape = RoundedCornerShape(26.dp),
                color = ChipDarkCard.copy(alpha = 0.94f),
                shadowElevation = 12.dp,
                border = androidx.compose.foundation.BorderStroke(1.dp, ChipBlue.copy(alpha = 0.5f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    navItems.forEachIndexed { index, item ->
                        val isSelected = uiState.selectedTab == index
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(14.dp))
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) { viewModel.onSelectTab(index) }
                                .padding(vertical = 4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(if (isSelected) ChipDarkElevated else Color.Transparent)
                                    .padding(horizontal = 12.dp, vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title,
                                    tint = if (isSelected) ChipBlueBright else ChipMutedSky.copy(alpha = 0.5f),
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = item.title,
                                fontSize = 10.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) ChipBlueBright else ChipMutedSky.copy(alpha = 0.65f)
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(ChipBgGradient)
                .padding(innerPadding)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                ChipHeader(
                    title = "Chipped Pass",
                    subtitle = "Parabolic Scoop & Defensive Bypassing Engine"
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    when (uiState.selectedTab) {
                        0 -> ChippedMatchesTab(
                            uiState = uiState,
                            onSelectClash = { viewModel.onSelectClash(it) },
                            onSelectArchetype = { viewModel.onSelectArchetype(it) }
                        )
                        1 -> ChippedSimulatorTab(
                            uiState = uiState,
                            onUpdateSim = { loft, dist, wall ->
                                viewModel.updateSimulation(loft, dist, wall)
                            }
                        )
                        2 -> TrajectoryArchetypesTab(
                            uiState = uiState,
                            onSelectArchetype = { viewModel.onSelectArchetype(it) }
                        )
                        3 -> LoftDrillsTab(
                            uiState = uiState,
                            onSelectDrill = { viewModel.onSelectDrill(it) }
                        )
                    }
                }
            }

            // Active Deep Dive Modals
            uiState.activeArchetypeModal?.let { archetype ->
                TrajectoryDetailModal(
                    archetype = archetype,
                    onDismiss = { viewModel.dismissModal() }
                )
            }

            uiState.activeDrillModal?.let { drill ->
                LoftDrillDetailModal(
                    drill = drill,
                    onDismiss = { viewModel.dismissModal() }
                )
            }

            uiState.activeClashModal?.let { clash ->
                ChippedMatchDetailModal(
                    clash = clash,
                    onDismiss = { viewModel.dismissModal() }
                )
            }
        }
    }
}
