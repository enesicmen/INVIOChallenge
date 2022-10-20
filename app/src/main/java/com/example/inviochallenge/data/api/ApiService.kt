package com.example.inviochallenge.data.api

import com.example.inviochallenge.data.api.response.MovieDetailApiResponse
import com.example.inviochallenge.data.api.response.SearchApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    fun searchMovies(@Query("s") searchText: String):Call<SearchApiResponse>

    @GET(".")
    fun getMovieDetail(@Query("i") movieId: String):Call<MovieDetailApiResponse>
}

