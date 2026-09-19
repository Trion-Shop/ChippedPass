package com.megapari.chippedpass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.megapari.chippedpass.core.theme.ChippedPassTheme
import com.megapari.chippedpass.presentation.ui.screens.ChippedPassScreen
import com.megapari.chippedpass.presentation.viewmodel.ChippedViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: ChippedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChippedPassTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                ) {
                    ChippedPassScreen(viewModel = viewModel)
                }
            }
        }
    }
}
