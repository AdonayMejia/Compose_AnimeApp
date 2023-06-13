package com.example.animeapp.ui.searchview.uistate

import com.example.domain.search.model.AnimeModel
import com.example.domain.search.model.AnimeSort
import com.example.domain.search.model.AnimeType

data class SearchUiState(
    val addToFavorites: (AnimeModel) -> Unit = {},
    val favoriteAnime: Set<Int> = emptySet(),
    val onTypeChanged: (AnimeType) -> Unit = {},
    val onSortChanged: (AnimeSort) -> Unit = {},
    val onSearchChanged: (String) -> Unit = {}
)
