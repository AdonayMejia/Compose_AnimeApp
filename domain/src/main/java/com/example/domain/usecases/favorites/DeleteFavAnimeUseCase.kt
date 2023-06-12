package com.example.domain.usecases.favorites

import com.example.domain.animerepository.AnimeRoomRepository
import javax.inject.Inject

class DeleteFavAnimeUseCase @Inject constructor(
    private val animeRoomRepository: AnimeRoomRepository
) {
    suspend operator fun invoke(animeId:Int){
        animeRoomRepository.deleteFavAnime(animeId)
    }
}