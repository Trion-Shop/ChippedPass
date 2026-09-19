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
import com.megapari.chippedpass.domain.model.ChippedPassMatchClash
import com.megapari.chippedpass.presentation.ui.components.ChippedMatchCard
import com.megapari.chippedpass.presentation.viewmodel.ChippedUiState

@Composable
fun ChippedMatchesTab(
    uiState: ChippedUiState,
    onSelectClash: (ChippedPassMatchClash) -> Unit,
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
                    text = "HISTORIC CHIPPED DUELS",
                    color = ChipBlue,
                    fontSize = 16.sp,
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
