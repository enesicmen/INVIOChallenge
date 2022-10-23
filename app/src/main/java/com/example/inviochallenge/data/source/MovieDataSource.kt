package com.example.inviochallenge.data.source

import com.example.inviochallenge.data.model.Movie
import com.example.inviochallenge.data.Resource

interface MovieDataSource {

    suspend fun searchMovies(searchText: String): Resource<List<Movie>>

    suspend fun saveMovies(movieList: List<Movie>)

    suspend fun saveMovie(movie: Movie)

    suspend fun getMovieDetail(movieId: String): Resource<Movie>
}