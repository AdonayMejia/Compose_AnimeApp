package com.example.domain.usecases

import com.example.domain.animeclient.AnimeClient
import com.example.domain.search.model.AnimeModel

class GetAnimeUseCase(
    private val animeClient: AnimeClient
) {
    suspend fun getExecute():List<AnimeModel>{
        return animeClient
            .getAnimeList()
    }
}