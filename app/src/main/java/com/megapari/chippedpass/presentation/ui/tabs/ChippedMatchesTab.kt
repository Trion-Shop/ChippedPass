package com.megapari.chippedpass.presentation.ui.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.megapari.chippedpass.core.theme.*
import com.megapari.chippedpass.domain.model.ChippedPassMatchClash
import com.megapari.chippedpass.domain.model.ChippedTrajectoryArchetype
import com.megapari.chippedpass.presentation.ui.components.ChippedMatchCard
import com.megapari.chippedpass.presentation.viewmodel.ChippedUiState

@Composable
fun ChippedMatchesTab(
    uiState: ChippedUiState,
    onSelectClash: (ChippedPassMatchClash) -> Unit,
    onSelectArchetype: (ChippedTrajectoryArchetype) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp)
    ) {
        // Carousel of Lofted Trajectory Archetypes
        item {
            Column {
                Text(
                    text = "AERIAL SCOOP ARCHETYPES",
                    color = ChipBlueBright,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(uiState.archetypes, key = { it.id }) { archetype ->
                        Card(
                            modifier = Modifier
                                .width(205.dp)
                                .clickable { onSelectArchetype(archetype) }
                                .border(1.dp, ChipBorder, RoundedCornerShape(14.dp)),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = ChipDarkCard)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = archetype.techniqueTitle,
                                        color = ChipWhite,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Box(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .background(ChipDarkElevated)
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "${archetype.clearanceApexMeters}m apex",
                                            color = ChipBlueBright,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                                Text(
                                    text = archetype.deliveryType,
                                    color = ChipRedFlame,
                                    fontSize = 10.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = archetype.biomechanicsBreakdown,
                                    color = ChipSoftSilver,
                                    fontSize = 10.sp,
                                    maxLines = 2,
                                    lineHeight = 13.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            Column {
                Text(
                    text = "HISTORIC CHIPPED DUELS",
                    color = ChipBlue,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.8.sp
                )
                Text(
                    text = "Legendary matches decided by majestic aerial scoops from Messi, Pirlo, and Modrić.",
                    color = ChipMutedSky,
                    fontSize = 12.sp
                )
            }
        }

        items(uiState.clashes, key = { it.id }) { clash ->
            ChippedMatchCard(
                clash = clash,
                onClick = { onSelectClash(clash) }
            )
        }
    }
}
