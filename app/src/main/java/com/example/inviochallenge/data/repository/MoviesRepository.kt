package com.example.inviochallenge.data.repository

import android.content.Context
import com.example.inviochallenge.data.DataCallback
import com.example.inviochallenge.data.model.Movie
import com.example.inviochallenge.data.source.MovieDataSource
import com.example.inviochallenge.di.qualifier.MovieDataSourceLocal
import com.example.inviochallenge.di.qualifier.MovieDataSourceRemote
import com.example.inviochallenge.ui.common.ext.isConnected
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    @ApplicationContext private var context: Context,
    @MovieDataSourceLocal private var movieLocalDataSource: MovieDataSource,
    @MovieDataSourceRemote private var movieRemoteDataSource: MovieDataSource
) {

    fun getMovieList(searchText: String,callback: DataCallback<List<Movie>>) {
        if(context.isConnected){
            movieRemoteDataSource.searchMovies(searchText,callback)
        }else {
            movieLocalDataSource.searchMovies(searchText,callback)
        }
    }
}