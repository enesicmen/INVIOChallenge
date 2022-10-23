package com.example.inviochallenge.data.remote

import com.example.inviochallenge.data.remote.response.SearchApiResponse
import com.example.inviochallenge.data.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    suspend fun searchMovies(@Query("s") searchText: String): Response<SearchApiResponse>

    @GET(".")
    suspend fun getMovieDetail(@Query("i") movieId: String): Response<Movie>
}