package com.megapari.chippedpass.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.megapari.chippedpass.core.theme.*
import com.megapari.chippedpass.presentation.ui.components.*
import com.megapari.chippedpass.presentation.ui.tabs.*
import com.megapari.chippedpass.presentation.viewmodel.ChippedViewModel

@Composable
fun ChippedPassScreen(
    viewModel: ChippedViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    val tabTitles = listOf("Simulator", "Archetypes", "Drills", "Matches")

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ChipBgGradient)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ChipHeader(
                title = "Chipped Pass",
                subtitle = "Parabolic Scoop & Defensive Bypassing Engine"
            )

            TabRow(
                selectedTabIndex = uiState.selectedTab,
                containerColor = ChipDarkBg,
                contentColor = ChipBlueBright,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[uiState.selectedTab]),
                        color = ChipBlueBright,
                        height = 3.dp
                    )
                },
                divider = {
                    HorizontalDivider(color = ChipBorder.copy(alpha = 0.5f))
                }
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = uiState.selectedTab == index,
                        onClick = { viewModel.onSelectTab(index) },
                        text = {
                            Text(
                                text = title,
                                fontSize = 13.sp,
                                fontWeight = if (uiState.selectedTab == index) FontWeight.Bold else FontWeight.Medium,
                                color = if (uiState.selectedTab == index) ChipBlueBright else ChipMutedSky
                            )
                        }
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                when (uiState.selectedTab) {
                    0 -> ChippedSimulatorTab(
                        uiState = uiState,
                        onUpdateSim = { loft, dist, wall ->
                            viewModel.updateSimulation(loft, dist, wall)
                        }
                    )
                    1 -> TrajectoryArchetypesTab(
                        uiState = uiState,
                        onSelectArchetype = { viewModel.onSelectArchetype(it) }
                    )
                    2 -> LoftDrillsTab(
                        uiState = uiState,
                        onSelectDrill = { viewModel.onSelectDrill(it) }
                    )
                    3 -> ChippedMatchesTab(
                        uiState = uiState,
                        onSelectClash = { viewModel.onSelectClash(it) }
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
