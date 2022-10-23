package com.example.inviochallenge.data.repository

import android.content.Context
import com.example.inviochallenge.data.Resource
import com.example.inviochallenge.data.model.Movie
import com.example.inviochallenge.data.source.MovieDataSource
import com.example.inviochallenge.di.qualifier.MovieDataSourceLocal
import com.example.inviochallenge.di.qualifier.MovieDataSourceRemote
import com.example.inviochallenge.ui.common.ext.isConnected
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MovieRepository @Inject constructor(
    @ApplicationContext private var context: Context,
    @MovieDataSourceRemote private var movieRemoteDataSource: MovieDataSource,
    @MovieDataSourceLocal private var movieLocalDataSource: MovieDataSource,
) {

    suspend fun getMovieList(searchText: String): Resource<List<Movie>> {
        return if(context.isConnected){
            val result = movieRemoteDataSource.searchMovies(searchText)
            if (result is Resource.Success){
                movieLocalDataSource.saveMovies(movieList = result.data!!)
            }
            result
        } else {
            movieLocalDataSource.searchMovies(searchText)
        }
    }

    suspend fun getMovieDetail(movieId: String): Resource<Movie> {
        return if(context.isConnected){
            val result = movieRemoteDataSource.getMovieDetail(movieId)
            if (result is Resource.Success){
                movieLocalDataSource.saveMovie(movie = result.data!!)
            }
            return result
        } else {
            movieLocalDataSource.getMovieDetail(movieId)
        }
    }
}