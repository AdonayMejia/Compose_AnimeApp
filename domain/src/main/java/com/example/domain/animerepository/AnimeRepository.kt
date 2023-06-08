package com.example.domain.animerepository

import com.example.domain.details.model.AnimeDetails
import com.example.domain.details.model.Character
import com.example.domain.search.model.AnimeModel

interface AnimeRepository {
    suspend fun getAnimeList():List<AnimeModel>
    suspend fun getAnimeDetail(id:Int): AnimeDetails

    suspend fun getAnimeCharacters(id: Int): Character
}