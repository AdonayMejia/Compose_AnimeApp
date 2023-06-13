package com.example.data.mapper.apollo

import com.example.data.GetAnimeDetailsQuery
import com.example.domain.details.model.AnimeDetails
import com.example.domain.details.model.Character

fun GetAnimeDetailsQuery.Media.toAnimeDetails(): AnimeDetails {
    return AnimeDetails(
        id = id,
        title = title?.romaji.orEmpty(),
        englishTitle = title?.english.orEmpty(),
        nativeTitle = title?.native.orEmpty(),
        banner = bannerImage.orEmpty(),
        episodes = episodes,
        genre = genres?.mapNotNull { it },
        description = description.orEmpty(),
        character = characters?.nodes?.mapNotNull { character ->
            character?.let {
                Character(
                    id = it.id,
                    name = it.name?.first.orEmpty(),
                    image = it.image?.medium.orEmpty(),
                    description = it.description
                )
            }
        },
        score = meanScore
    )
}
