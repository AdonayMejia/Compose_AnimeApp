package com.example.animeapp.ui.characterview.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.details.model.Character
import com.example.domain.usecases.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _characterDetails = MutableStateFlow<Character?>(null)
    val characterDetails: StateFlow<Character?> = _characterDetails

    fun fetchCharacterDetails(id: Int) {
        viewModelScope.launch {
            try {
                val details = getCharactersUseCase(id)
                _characterDetails.value = details
            } catch (e: Exception) {
                Log.d("Error", "${e.message}")
            }
        }
    }

}