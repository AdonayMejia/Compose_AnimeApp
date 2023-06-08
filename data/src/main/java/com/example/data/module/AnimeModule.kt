package com.example.data.module

import com.apollographql.apollo3.ApolloClient
import com.example.data.apolloanimeclient.AnimeRepositoryImpl
import com.example.domain.animerepository.AnimeRepository
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
     fun providesAnimeClient(apolloClient: ApolloClient) : AnimeRepository {
         return AnimeRepositoryImpl(apolloClient)
     }

    @Provides
    @Singleton
    fun providesGetAnimeUseCase(animeRepository: AnimeRepository) : GetAnimeUseCase {
        return GetAnimeUseCase(animeRepository)
    }

    @Provides
    @Singleton
    fun providesGetCharacterUseCase(animeRepository: AnimeRepository) : GetCharactersUseCase {
        return GetCharactersUseCase(animeRepository)
    }
}