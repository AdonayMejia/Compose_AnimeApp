package com.example.domain.usecases

import com.example.domain.animerepository.AnimeRepository
import com.example.domain.details.model.AnimeDetails
import com.example.domain.details.model.Character
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val animeRepository: AnimeRepository
) {
    suspend operator fun invoke(id:Int):Character{
        return animeRepository.getAnimeCharacters(id)
    }
}