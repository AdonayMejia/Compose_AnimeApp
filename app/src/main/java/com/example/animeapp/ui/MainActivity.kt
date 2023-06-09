package com.example.animeapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animeapp.ui.searchview.SearchAnimeScreen
import com.example.animeapp.ui.searchview.viewmodel.SearchViewModel
import com.example.animeapp.ui.theme.AnimeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_android_components_ViewModelComponent

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimeAppTheme {
                val viewModel = hiltViewModel<SearchViewModel>()
                val state by viewModel.uiState.collectAsState()
                SearchAnimeScreen(
                    state = state,
                    onSelectAnime = viewModel::selectedAnime,
//                    onDismissAnime = {}
                )
            }
        }
    }
}
