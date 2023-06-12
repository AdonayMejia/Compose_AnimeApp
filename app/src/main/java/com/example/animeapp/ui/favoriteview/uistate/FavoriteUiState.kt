package com.example.animeapp.ui.favoriteview.uistate

import com.example.domain.room.model.FavoriteAnime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class FavoriteUiState(
    val removeFavoriteAnime: (Int) -> Unit = {},
    val favoriteAnimesList: StateFlow<List<FavoriteAnime>> = MutableStateFlow(emptyList())
)
