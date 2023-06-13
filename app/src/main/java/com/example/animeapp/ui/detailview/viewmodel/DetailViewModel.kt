package com.example.animeapp.ui.detailview.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.details.model.AnimeDetails
import com.example.domain.usecases.GetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getAnimeDetailsUseCase: GetDetailsUseCase,
) : ViewModel() {
    private val _animeDetails = MutableStateFlow<AnimeDetails?>(null)
    val animeDetails: StateFlow<AnimeDetails?> = _animeDetails

    fun fetchAnimeDetails(id: Int) {
        viewModelScope.launch {
            try {
                val details = getAnimeDetailsUseCase(id)
                _animeDetails.value = details
            } catch (e: Exception) {
                Log.d("Error","${e.message}")
            }
        }
    }
}
