package com.example.data.room.repository

import com.example.data.mapper.room.toAnime
import com.example.data.mapper.room.toAnimeEntity
import com.example.data.room.AnimeDao
import com.example.data.room.AnimeEntity
import com.example.domain.animerepository.AnimeRoomRepository
import com.example.domain.room.model.FavoriteAnime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnimeRoomRepositoryImpl @Inject constructor(
    private val animeDao: AnimeDao
) : AnimeRoomRepository {

    override val getAllAnime: Flow<List<FavoriteAnime>>
        get() = animeDao.getFavoriteAnime()
            .map {
                it.map(AnimeEntity::toAnime)
            }


    override suspend fun addFavoriteAnime(anime: FavoriteAnime) {
        return animeDao.addAnimeToFavorite(anime.toAnimeEntity())
    }

    override suspend fun deleteFavAnime(id: Int) {
        return animeDao.deleteAnime(id)
    }
}
