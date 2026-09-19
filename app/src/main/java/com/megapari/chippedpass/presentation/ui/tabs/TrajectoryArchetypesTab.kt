package com.megapari.chippedpass.presentation.ui.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.megapari.chippedpass.core.theme.ChipBlue
import com.megapari.chippedpass.core.theme.ChipMutedSky
import com.megapari.chippedpass.domain.model.ChippedTrajectoryArchetype
import com.megapari.chippedpass.presentation.ui.components.TrajectoryArchetypeCard
import com.megapari.chippedpass.presentation.viewmodel.ChippedUiState

@Composable
fun TrajectoryArchetypesTab(
    uiState: ChippedUiState,
    onSelectArchetype: (ChippedTrajectoryArchetype) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp)
    ) {
        item {
            Column {
                Text(
                    text = "AERIAL CHIP ARCHETYPES",
                    color = ChipBlue,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.8.sp
                )
                Text(
                    text = "Biomechanical scoop strikes, backspin generators, and trivela curves.",
                    color = ChipMutedSky,
                    fontSize = 12.sp
                )
            }
        }

        items(uiState.archetypes, key = { it.id }) { archetype ->
            TrajectoryArchetypeCard(
                archetype = archetype,
                onClick = { onSelectArchetype(archetype) }
            )
        }
    }
}
