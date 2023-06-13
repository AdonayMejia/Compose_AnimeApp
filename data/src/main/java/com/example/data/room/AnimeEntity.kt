package com.example.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteAnime")
class AnimeEntity(
    @PrimaryKey
    val animeId: Int,

    @ColumnInfo("animeTitle")
    val animeTitle: String,

    @ColumnInfo("Banner")
    val animeBanner: String,
)
