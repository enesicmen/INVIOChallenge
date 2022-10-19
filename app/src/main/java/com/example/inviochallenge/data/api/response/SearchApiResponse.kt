package com.example.inviochallenge.data.api.response

import com.example.inviochallenge.data.model.Movie
import com.google.gson.annotations.SerializedName

data class SearchApiResponse(

    @SerializedName("Search")
    val movies: List<Movie>
)