package com.example.data.animerepositoryimpl

import com.apollographql.apollo3.ApolloClient
import com.example.data.CharactersQuery
import com.example.data.GetAnimeDetailsQuery
import com.example.data.ListAnimeQuery
import com.example.data.apolloextensions.executeQuery
import com.example.data.apolloextensions.toOptional
import com.example.data.mapper.apollo.toAnimeCharacter
import com.example.data.mapper.apollo.toAnimeDetails
import com.example.data.mapper.apollo.toAnimeMediaType
import com.example.data.mapper.apollo.toAnimeModel
import com.example.data.mapper.apollo.toMediaSort

import com.example.domain.animerepository.AnimeRepository
import com.example.domain.details.model.AnimeDetails
import com.example.domain.details.model.Character
import com.example.domain.search.model.AnimeModel
import com.example.domain.search.model.AnimeSort
import com.example.domain.search.model.AnimeType
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : AnimeRepository {

    override suspend fun getAnimeList(
        page: Int,
        perPage: Int,
        search: String?,
        sort: List<AnimeSort>,
        type: AnimeType
    ): List<AnimeModel> {
        if (search.isNullOrEmpty()) {
            return emptyList()
        }
        val query = ListAnimeQuery(
            page = page.toOptional(),
            perPage = perPage.toOptional(),
            type = type.toAnimeMediaType().toOptional(),
            search = search.toOptional(),
            sort = sort.map { it.toMediaSort() }.toOptional()
        )
        return apolloClient.executeQuery(query) { data ->
            data.Page?.media?.mapNotNull { it?.toAnimeModel() } ?: emptyList()
        }
    }

    override suspend fun getAnimeDetail(id: Int): AnimeDetails {
        val query = GetAnimeDetailsQuery(id.toOptional())
        return apolloClient.executeQuery(query) { it.Media?.toAnimeDetails() }
    }

    override suspend fun getAnimeCharacters(id: Int): Character {
        val query = CharactersQuery(id.toOptional())
        return apolloClient.executeQuery(query) { it.Character?.toAnimeCharacter() }
    }

}
