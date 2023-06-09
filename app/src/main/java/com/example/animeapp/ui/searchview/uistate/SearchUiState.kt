package com.example.animeapp.ui.searchview.uistate

import com.example.domain.details.model.AnimeDetails
import com.example.domain.search.model.AnimeModel

data class SearchUiState(
    val isLoading:Boolean,
    val selectedAnime:AnimeDetails?,
    val anime:List<AnimeModel>,
    val onSelectAnime:(Int) -> Unit
)
