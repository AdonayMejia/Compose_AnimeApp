package com.example.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAnimeToFavorite(anime:AnimeEntity)

    @Query("DELETE FROM FavoriteAnime WHERE animeId = :id")
    suspend fun deleteAnime(id:Int)

    @Query("SELECT * FROM FavoriteAnime")
    fun getFavoriteAnime() : Flow<List<AnimeEntity>>

}