package com.example.domain.animerepository

import com.example.domain.details.model.AnimeDetails
import com.example.domain.details.model.Character
import com.example.domain.search.model.AnimeModel
import com.example.domain.search.model.AnimeSort
import com.example.domain.search.model.AnimeType

interface AnimeRepository {
    suspend fun getAnimeList(
        page:Int,
        perPage:Int,
        search:String? = null,
        sort:List<AnimeSort>,
        type:AnimeType
    ):List<AnimeModel>
    suspend fun getAnimeDetail(id:Int): AnimeDetails

    suspend fun getAnimeCharacters(id: Int): Character
}