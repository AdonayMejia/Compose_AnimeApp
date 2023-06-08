package com.example.animeapp.ui.searchview.uistate

import com.example.domain.details.model.AnimeDetails
import com.example.domain.search.model.AnimeModel

data class SearchUiState(
    val isLoading:Boolean = false,
    val selectedAnime:AnimeDetails? = null,
    val anime:List<AnimeModel> = emptyList()
)
