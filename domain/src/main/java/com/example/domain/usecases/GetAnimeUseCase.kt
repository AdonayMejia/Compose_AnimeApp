package com.example.domain.usecases

import com.example.domain.animerepository.AnimeRepository
import com.example.domain.search.model.AnimeModel
import com.example.domain.search.model.AnimeSort
import com.example.domain.search.model.AnimeType
import javax.inject.Inject

class GetAnimeUseCase @Inject constructor(
    private val animeRepository: AnimeRepository
) {
    suspend fun getExecute(
        page:Int,
        perPage:Int,
        search:String? = null,
        sort:List<AnimeSort>,
        type: AnimeType
    ):List<AnimeModel>{
        return animeRepository.getAnimeList(page, perPage, search, sort, type)
    }
}