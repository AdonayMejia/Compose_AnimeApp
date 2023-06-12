package com.example.data.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.data.room.AnimeDao
import com.example.data.room.AnimeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


private const val DATABASE_NAME = "FavoriteAnime"
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesAnimeDataBase(@ApplicationContext context: Context) : AnimeDatabase {
        return Room.databaseBuilder(
            context,
            AnimeDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun providesAnimeDao(database: AnimeDatabase) : AnimeDao {
        return database.animeDao()
    }
}