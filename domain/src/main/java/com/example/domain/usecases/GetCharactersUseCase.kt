package com.example.domain.usecases

import com.example.domain.animeclient.AnimeClient
import com.example.domain.details.model.AnimeDetails

class GetCharactersUseCase(
    private val animeClient: AnimeClient
) {
    suspend fun getExecute(id:Int):AnimeDetails{
        return animeClient.getAnimeDetail(id)
    }
}