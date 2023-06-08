package com.example.domain.usecases

import com.example.domain.animerepository.AnimeRepository
import com.example.domain.search.model.AnimeModel

class GetAnimeUseCase(
    private val animeRepository: AnimeRepository
) {
    suspend fun getExecute():List<AnimeModel>{
        return animeRepository
            .getAnimeList()
    }
}