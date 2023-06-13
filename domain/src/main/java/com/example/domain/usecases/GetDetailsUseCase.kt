package com.example.domain.usecases

import com.example.domain.animerepository.AnimeRepository
import com.example.domain.details.model.AnimeDetails
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(
    private val animeRepository: AnimeRepository

) {
    suspend operator fun invoke(id: Int): AnimeDetails {
        return animeRepository.getAnimeDetail(id)
    }
}
