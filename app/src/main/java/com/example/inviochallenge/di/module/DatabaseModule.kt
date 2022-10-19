package com.example.inviochallenge.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inviochallenge.data.Constants
import com.example.inviochallenge.data.local.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase) = movieDatabase.movieDao()

    @Provides
    fun provideMovieDatabase(movieDataBaseBuilder: RoomDatabase.Builder<MovieDatabase?>) =
        movieDataBaseBuilder
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideMovieDatabaseBuilder(@ApplicationContext context: Context?) =
        Room.databaseBuilder(
            context!!,
            MovieDatabase::class.java,
            Constants.Database.NAME
        )
}