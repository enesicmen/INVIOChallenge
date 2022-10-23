package com.example.inviochallenge.data.source

import com.example.inviochallenge.data.Resource
import com.example.inviochallenge.data.remote.ApiService
import com.example.inviochallenge.data.model.Movie
import javax.inject.Inject

class MovieRemoteDataSource
@Inject constructor(
    private val apiService: ApiService,
):MovieDataSource{

    override suspend fun searchMovies(searchText: String): Resource<List<Movie>> {
        val response = apiService.searchMovies(searchText)
        return if (response.isSuccessful && response.body()?.movies != null){
            Resource.Success(response.body()!!.movies!!)
        } else {
            Resource.Error(response.errorBody().toString())
        }
    }

    override suspend fun getMovieDetail(movieId: String): Resource<Movie> {
        val response = apiService.getMovieDetail(movieId)
        return if (response.isSuccessful && response.body() != null){
            Resource.Success(response.body()!!)
        } else {
            Resource.Error(response.errorBody().toString())
        }
    }

    override suspend fun saveMovies(movieList: List<Movie>) {}

    override suspend fun saveMovie(movie: Movie) {}
}