package com.example.domain.usecases.favorites

import com.example.domain.animerepository.AnimeRoomRepository
import com.example.domain.room.model.FavoriteAnime
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimesFavUseCase @Inject constructor(
    private val animeRoomRepository: AnimeRoomRepository
) {
    operator fun invoke(): Flow<List<FavoriteAnime>> {
        return animeRoomRepository.getAllAnime
    }
}
