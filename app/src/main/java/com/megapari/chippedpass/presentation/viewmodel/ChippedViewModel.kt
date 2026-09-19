package com.megapari.chippedpass.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.megapari.chippedpass.data.InMemoryChippedPassRepository
import com.megapari.chippedpass.domain.model.*
import com.megapari.chippedpass.domain.repository.ChippedPassRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ChippedUiState(
    val selectedTab: Int = 0,
    val archetypes: List<ChippedTrajectoryArchetype> = emptyList(),
    val drills: List<LoftAccuracyDrill> = emptyList(),
    val clashes: List<ChippedPassMatchClash> = emptyList(),
    // Simulator
    val simLoftAngle: Float = 45.0f,
    val simDistanceMeters: Float = 28.0f,
    val simWallHeightMeters: Float = 1.95f,
    val simulationResult: ChippedSimulationResult? = null,
    // Modals
    val activeArchetypeModal: ChippedTrajectoryArchetype? = null,
    val activeDrillModal: LoftAccuracyDrill? = null,
    val activeClashModal: ChippedPassMatchClash? = null
)

class ChippedViewModel(
    private val repository: ChippedPassRepository = InMemoryChippedPassRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChippedUiState())
    val uiState: StateFlow<ChippedUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val archs = repository.getTrajectoryArchetypes()
            val drs = repository.getLoftDrills()
            val cls = repository.getMatchClashes()
            val sim = repository.calculateChippedSimulation(45.0f, 28.0f, 1.95f)

            _uiState.update {
                it.copy(
                    archetypes = archs,
                    drills = drs,
                    clashes = cls,
                    simulationResult = sim
                )
            }
        }
    }

    fun onSelectTab(tabIndex: Int) {
        _uiState.update { it.copy(selectedTab = tabIndex) }
    }

    fun onSelectArchetype(archetype: ChippedTrajectoryArchetype) {
        _uiState.update { it.copy(activeArchetypeModal = archetype) }
    }

    fun onSelectDrill(drill: LoftAccuracyDrill) {
        _uiState.update { it.copy(activeDrillModal = drill) }
    }

    fun onSelectClash(clash: ChippedPassMatchClash) {
        _uiState.update { it.copy(activeClashModal = clash) }
    }

    fun dismissModal() {
        _uiState.update {
            it.copy(
                activeArchetypeModal = null,
                activeDrillModal = null,
                activeClashModal = null
            )
        }
    }

    fun updateSimulation(loftAngle: Float, distance: Float, wallHeight: Float) {
        val sim = repository.calculateChippedSimulation(loftAngle, distance, wallHeight)
        _uiState.update {
            it.copy(
                simLoftAngle = loftAngle,
                simDistanceMeters = distance,
                simWallHeightMeters = wallHeight,
                simulationResult = sim
            )
        }
    }
}
