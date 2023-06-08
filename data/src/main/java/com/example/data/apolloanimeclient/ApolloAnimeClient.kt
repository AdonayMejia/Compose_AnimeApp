package com.example.data.apolloanimeclient

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.example.data.AnimeCharactersQuery
import com.example.data.ListAnimeQuery
import com.example.data.mapper.toAnimeDetails
import com.example.data.mapper.toAnimeModel
import com.example.domain.animeclient.AnimeClient
import com.example.domain.details.model.AnimeDetails
import com.example.domain.search.model.AnimeModel

class ApolloAnimeClient(
    private val apolloClient: ApolloClient
) : AnimeClient {
    override suspend fun getAnimeList(): List<AnimeModel> {
        return apolloClient
            .query(ListAnimeQuery())
            .execute()
            .data
            ?.Page
            ?.media
            ?.map { it!!.toAnimeModel() }
            ?: emptyList()
    }

    override suspend fun getAnimeDetail(id: Int): AnimeDetails {
        return apolloClient
            .query(AnimeCharactersQuery(Optional.presentIfNotNull(id)))
            .execute()
            .data
            ?.Media
            ?.toAnimeDetails()!!
    }
}