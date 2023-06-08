package com.example.data.mapper.apollo

import com.example.data.CharactersQuery
import com.example.domain.details.model.Character

fun CharactersQuery.Character.toAnimeCharacter():Character{
    return Character(
        id = id,
        name = name?.full.orEmpty(),
        image = image?.large.orEmpty(),
        description = description
    )
}