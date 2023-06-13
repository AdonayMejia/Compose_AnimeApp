package com.example.animeapp.ui.favoriteview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.ui.favoriteview.uistate.FavoriteUiState
import com.example.animeapp.ui.favoriteview.uistate.UiState
import com.example.domain.room.model.FavoriteAnime
import com.example.domain.usecases.favorites.AddFavoriteAnimeUseCase
import com.example.domain.usecases.favorites.DeleteFavAnimeUseCase
import com.example.domain.usecases.favorites.GetAnimesFavUseCase
import com.example.domain.usecases.favorites.UpdateFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAnimesFavUseCase: GetAnimesFavUseCase,
    private val removeAnimeUseCase: DeleteFavAnimeUseCase,
    private val updateFavAnime: UpdateFavoriteUseCase
) : ViewModel(){

    private val _favoriteAnimesList = MutableStateFlow<List<FavoriteAnime>>(emptyList())

    private val _uiState = MutableStateFlow(
        UiState(
            favAnime = emptySet()
        )
    )

    private val _uiStateFavorite = MutableStateFlow(
        FavoriteUiState(
            removeFavoriteAnime = this::removeFromFavorites,
            favoriteAnimesList = _favoriteAnimesList
        )
    )

    val uiStateFavorite = _uiStateFavorite.asStateFlow()

    init {
        viewModelScope.launch {
            getAnimesFavUseCase()
                .onEach { favoriteAnimeList ->
                    _favoriteAnimesList.value = favoriteAnimeList
                    _uiState.value =
                        _uiState.value.copy(favAnime = favoriteAnimeList.map { it.id }.toSet())
                }.launchIn(viewModelScope)

            updateFavAnime().collect { updatedFavoriteAnime ->
                _uiState.value = _uiState.value.copy(favAnime = updatedFavoriteAnime)
            }
        }
    }

    private fun removeFromFavorites(animeId: Int) {
        viewModelScope.launch {
            if (_uiState.value.favAnime.contains(animeId)) {
                removeAnimeUseCase(animeId)
            }
        }
    }
}
