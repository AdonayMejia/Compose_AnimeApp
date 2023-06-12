package com.example.data.module

import com.example.data.animerepositoryimpl.AnimeRepositoryImpl
import com.example.data.room.repository.AnimeRoomRepositoryImpl
import com.example.domain.animerepository.AnimeRepository
import com.example.domain.animerepository.AnimeRoomRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    @Singleton
    abstract fun bindFavoriteRepository(
        animeRoomRepositoryImpl: AnimeRoomRepositoryImpl
    ): AnimeRoomRepository

    @Binds
    @Singleton
    abstract fun bindRepository(
        animeRepositoryImpl: AnimeRepositoryImpl
    ): AnimeRepository
}