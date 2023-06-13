package com.example.domain.usecases.favorites

import com.example.domain.animerepository.AnimeRoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UpdateFavoriteUseCase @Inject constructor(
    private val animeRoomRepository: AnimeRoomRepository
) {
    operator fun invoke(): Flow<Set<Int>> {
        return animeRoomRepository.getAllAnime.map { animeList ->
            animeList.map { it.id }.toSet()
        }
    }
}
