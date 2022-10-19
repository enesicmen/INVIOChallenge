package com.example.inviochallenge.di.module

import com.example.inviochallenge.data.source.MovieDataSource
import com.example.inviochallenge.data.source.MovieLocalDataSource
import com.example.inviochallenge.data.source.MovieRemoteDataSource
import com.example.inviochallenge.di.qualifier.MovieDataSourceLocal
import com.example.inviochallenge.di.qualifier.MovieDataSourceRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @MovieDataSourceLocal
    abstract fun bindMovieBranchLocalDataSource(movieLocalDataSource: MovieLocalDataSource): MovieDataSource

    @Binds
    @MovieDataSourceRemote
    abstract fun bindMovieBranchRemoteDataSource(movieRemoteDataSource: MovieRemoteDataSource): MovieDataSource
}