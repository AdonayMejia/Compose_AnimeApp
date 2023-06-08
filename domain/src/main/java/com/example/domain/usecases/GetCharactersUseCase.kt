package com.example.domain.usecases

import com.example.domain.animerepository.AnimeRepository
import com.example.domain.details.model.AnimeDetails

class GetCharactersUseCase(
    private val animeRepository: AnimeRepository
) {
    suspend fun getExecute(id:Int):AnimeDetails{
        return animeRepository.getAnimeDetail(id)
    }
}