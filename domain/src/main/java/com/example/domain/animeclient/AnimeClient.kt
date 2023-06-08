package com.example.domain.animeclient

import com.example.domain.details.model.AnimeDetails
import com.example.domain.search.model.AnimeModel

interface AnimeClient {
    suspend fun getAnimeList():List<AnimeModel>
    suspend fun getAnimeDetail(id:Int): AnimeDetails
}