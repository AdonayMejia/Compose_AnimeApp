package com.example.data.mapper

import com.example.data.AnimeCharactersQuery
import com.example.data.ListAnimeQuery
import com.example.domain.details.model.AnimeDetails
import com.example.domain.details.model.Character
import com.example.domain.search.model.AnimeModel

fun ListAnimeQuery.Medium.toAnimeModel():AnimeModel{
    return AnimeModel(
        id = this.id,
        name = this.title?.romaji.orEmpty(),
        image = this.coverImage?.extraLarge.orEmpty()
    )
}

fun AnimeCharactersQuery.Media.toAnimeDetails():AnimeDetails{
    return AnimeDetails(
        id = this.id,
        title = this.title?.romaji.orEmpty(),
        banner = this.coverImage?.extraLarge.orEmpty(),
        episodes = this.episodes,
        genre = this.genres?.mapNotNull { it },
        description = this.description.orEmpty(),
        character = this.characters?.nodes?.mapNotNull { character ->
            character?.let {
                Character(
                    id = it.id,
                    name = it.name?.first.orEmpty(),
                    image = it.image?.medium.orEmpty(),
                    description = it.description.orEmpty()
                )
            }
        }
    )
}
