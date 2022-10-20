package com.example.inviochallenge.data.source

import com.example.inviochallenge.data.DataCallback
import com.example.inviochallenge.data.api.response.MovieDetailApiResponse
import com.example.inviochallenge.data.local.MovieDao
import com.example.inviochallenge.data.model.Movie
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@OptIn(DelicateCoroutinesApi::class)
class MovieLocalDataSource
@Inject constructor(
    private val movieDao: MovieDao
):MovieDataSource{
    override fun searchMovies(searchText: String, callback: DataCallback<List<Movie>>) {

    }

    override fun saveMovieList(movieList: List<Movie>) {

    }

    override fun getMovieDetail(movieId: String, callback: DataCallback<MovieDetailApiResponse>) {

    }

}