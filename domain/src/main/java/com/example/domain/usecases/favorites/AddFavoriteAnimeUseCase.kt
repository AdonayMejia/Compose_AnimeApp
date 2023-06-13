package com.example.domain.usecases.favorites

import com.example.domain.animerepository.AnimeRoomRepository
import com.example.domain.room.model.FavoriteAnime
import com.example.domain.search.model.AnimeModel
import javax.inject.Inject

class AddFavoriteAnimeUseCase @Inject constructor(
    private val animeRoomRepository: AnimeRoomRepository
) {
    suspend operator fun invoke(animeModel: AnimeModel) {
        val favoriteAnime = FavoriteAnime(
            id = animeModel.id,
            animeTitle = animeModel.name,
            animeBanner = animeModel.image
        )
        animeRoomRepository.addFavoriteAnime(favoriteAnime)
    }
}
