package com.example.data.mapper.apollo

import com.example.data.ListAnimeQuery
import com.example.domain.details.model.AnimeDetails
import com.example.domain.details.model.Character
import com.example.domain.search.model.AnimeModel

fun ListAnimeQuery.Medium.toAnimeModel():AnimeModel{
    return AnimeModel(
        id = id,
        name = title?.romaji.orEmpty(),
        image = coverImage?.extraLarge.orEmpty()
    )
}
