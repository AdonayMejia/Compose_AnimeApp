package com.example.domain.usecases

import com.example.domain.animerepository.AnimeRepository
import com.example.domain.details.model.AnimeDetails
import com.example.domain.details.model.Character

class GetCharactersUseCase(
    private val animeRepository: AnimeRepository
) {
    suspend fun getExecute(id:Int):Character{
        return animeRepository.getAnimeCharacters(id)
    }
}