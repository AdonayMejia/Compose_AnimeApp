package com.example.data.module

import android.app.Application
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.example.data.animerepositoryimpl.AnimeRepositoryImpl
import com.example.data.room.repository.AnimeRoomRepositoryImpl
import com.example.data.room.AnimeDao
import com.example.data.room.AnimeDatabase
import com.example.domain.animerepository.AnimeRepository
import com.example.domain.animerepository.AnimeRoomRepository
import com.example.domain.usecases.GetAnimeUseCase
import com.example.domain.usecases.GetCharactersUseCase
import com.example.domain.usecases.GetDetailsUseCase
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

//    @Provides
//    @Singleton
//     fun providesAnimeClient(apolloClient: ApolloClient) : AnimeRepository {
//         return AnimeRepositoryImpl(apolloClient)
//     }
//
//    @Provides
//    @Singleton
//    fun providesGetAnimeUseCase(animeRepository: AnimeRepository) : GetAnimeUseCase {
//        return GetAnimeUseCase(animeRepository)
//    }
//
//    @Provides
//    @Singleton
//    fun providesGetCharacterUseCase(animeRepository: AnimeRepository) : GetCharactersUseCase {
//        return GetCharactersUseCase(animeRepository)
//    }
//
//    @Provides
//    @Singleton
//    fun providesGetCDetailsUseCase(animeRepository: AnimeRepository) : GetDetailsUseCase {
//        return GetDetailsUseCase(animeRepository)
//    }

}