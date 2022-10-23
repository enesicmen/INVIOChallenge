package com.example.inviochallenge.data.source

import com.example.inviochallenge.data.Resource
import com.example.inviochallenge.data.local.MovieDao
import com.example.inviochallenge.data.model.Movie
import javax.inject.Inject

class MovieLocalDataSource
@Inject constructor(
    private val movieDao: MovieDao
): MovieDataSource {

    override suspend fun searchMovies(searchText: String): Resource<List<Movie>> {
        val result = movieDao.searchMovie(searchText)
        return Resource.Success(result)
    }

    override suspend fun saveMovies(movieList: List<Movie>) {
        movieDao.saveMovies(movieList)
    }

    override suspend fun saveMovie(movie: Movie) {
        movieDao.saveMovie(movie)
    }

    override suspend fun getMovieDetail(movieId: String): Resource<Movie> {
        return movieDao.getMovieDetails(movieId)?.let {
            Resource.Success(it)
        } ?: run {
            Resource.Error("Movie is not found")
        }
    }
}