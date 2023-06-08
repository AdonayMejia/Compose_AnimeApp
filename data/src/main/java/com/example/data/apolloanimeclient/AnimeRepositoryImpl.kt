package com.example.data.apolloanimeclient

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.api.Query
import com.example.data.CharactersQuery
import com.example.data.GetAnimeDetailsQuery
import com.example.data.ListAnimeQuery
import com.example.data.apolloextensions.executeQuery
import com.example.data.apolloextensions.toOptional
import com.example.data.mapper.apollo.toAnimeCharacter
import com.example.data.mapper.apollo.toAnimeDetails
import com.example.data.mapper.apollo.toAnimeModel

import com.example.domain.animerepository.AnimeRepository
import com.example.domain.details.model.AnimeDetails
import com.example.domain.details.model.Character
import com.example.domain.search.model.AnimeModel

class AnimeRepositoryImpl(
    private val apolloClient: ApolloClient
) : AnimeRepository {

    override suspend fun getAnimeList(): List<AnimeModel> {
        return apolloClient
            .query(ListAnimeQuery())
            .execute()
            .data
            ?.Page
            ?.media
            ?.mapNotNull { it?.toAnimeModel() }
            ?: emptyList()
    }

    override suspend fun getAnimeDetail(id: Int): AnimeDetails {
       val query = GetAnimeDetailsQuery(id.toOptional())
        return apolloClient.executeQuery(query){it.Media?.toAnimeDetails()}
    }

    override suspend fun getAnimeCharacters(id: Int): Character {
        val query = CharactersQuery(id.toOptional())
        return apolloClient.executeQuery(query){it.Character?.toAnimeCharacter()}
    }

}
