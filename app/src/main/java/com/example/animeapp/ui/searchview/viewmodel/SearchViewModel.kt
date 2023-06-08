package com.example.animeapp.ui.searchview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.ui.searchview.uistate.SearchUiState
import com.example.domain.usecases.GetAnimeUseCase
import com.example.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAnimeUseCase: GetAnimeUseCase,
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel(){

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()
    init {
        viewModelScope.launch{
            _uiState.update { it.copy(
                isLoading = true
            ) }
            _uiState.update { it.copy(
                anime = getAnimeUseCase.getExecute(),
                isLoading = false
            ) }
        }
    }

     fun selectedAnime(id:Int){
        viewModelScope.launch {
            _uiState.update { it.copy(
                selectedAnime = getCharactersUseCase.getExecute(id)
            ) }
        }
    }

    private fun dismissAnimeDialog(){
        viewModelScope.launch {
            _uiState.update { it.copy(
                selectedAnime = null
            ) }
        }
    }

//    val searchUiState = mutableListOf(
//        SearchUiState(
//            isLoading = false,
//            selectedAnime = null,
//            anime = emptyList()
//        )
//    )
}