package com.example.inviochallenge.data.repository

import android.content.Context
import com.example.inviochallenge.data.source.MovieDataSource
import com.example.inviochallenge.di.qualifier.MovieDataSourceLocal
import com.example.inviochallenge.di.qualifier.MovieDataSourceRemote
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    @ApplicationContext private var context: Context,
    @MovieDataSourceLocal private var movieLocalDataSource: MovieDataSource,
    @MovieDataSourceRemote private var movieRemoteDataSource: MovieDataSource
) {
}