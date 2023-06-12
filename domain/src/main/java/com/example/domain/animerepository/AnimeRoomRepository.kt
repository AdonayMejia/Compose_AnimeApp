package com.example.domain.animerepository

import com.example.domain.room.model.FavoriteAnime
import kotlinx.coroutines.flow.Flow

interface AnimeRoomRepository {

    val getAllAnime: Flow<List<FavoriteAnime>>
    suspend fun addFavoriteAnime(anime:FavoriteAnime)
    suspend fun deleteFavAnime(id:Int)
}