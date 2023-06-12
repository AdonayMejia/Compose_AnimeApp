package com.example.data.mapper.room

import com.example.data.room.AnimeEntity
import com.example.domain.room.model.FavoriteAnime
import com.example.domain.search.model.AnimeModel

fun AnimeEntity.toAnime():FavoriteAnime {
    return FavoriteAnime(
        id = animeId,
        animeTitle = animeTitle,
        animeBanner = animeBanner
    )
}

fun FavoriteAnime.toAnimeEntity():AnimeEntity{
    return AnimeEntity(
        animeId = id,
        animeTitle = animeTitle,
        animeBanner = animeBanner
    )
}