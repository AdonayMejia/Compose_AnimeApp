package com.example.data.module

import com.apollographql.apollo3.ApolloClient
import com.example.data.apolloanimeclient.ApolloAnimeClient
import com.example.domain.animeclient.AnimeClient
import com.example.domain.usecases.GetAnimeUseCase
import com.example.domain.usecases.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnimeModule {
    @Provides
    @Singleton
    fun provideApolloClient() : ApolloClient{
        return ApolloClient.Builder()
            .serverUrl("https://graphql.anilist.co")
            .build()
    }

    @Provides
    @Singleton
     fun providesAnimeClient(apolloClient: ApolloClient) : AnimeClient {
         return ApolloAnimeClient(apolloClient)
     }

    @Provides
    @Singleton
    fun providesGetAnimeUseCase(animeClient: AnimeClient) : GetAnimeUseCase {
        return GetAnimeUseCase(animeClient)
    }

    @Provides
    @Singleton
    fun providesGetCharacterUseCase(animeClient: AnimeClient) : GetCharactersUseCase {
        return GetCharactersUseCase(animeClient)
    }
}