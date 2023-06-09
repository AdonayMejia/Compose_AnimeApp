package com.example.domain.usecases

import com.example.domain.animerepository.AnimeRepository
import com.example.domain.search.model.AnimeModel
import com.example.domain.search.model.AnimeSort
import com.example.domain.search.model.AnimeType

class GetAnimeUseCase(
    private val animeRepository: AnimeRepository
) {
    suspend fun getExecute(
        page:Int,
        search:String? = null,
        sort:List<AnimeSort>,
        type: AnimeType
    ):List<AnimeModel>{
        return animeRepository.getAnimeList(page, search, sort, type)
    }
}