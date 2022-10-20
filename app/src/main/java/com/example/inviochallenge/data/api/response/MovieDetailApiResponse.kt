package com.example.inviochallenge.data.api.response

import com.google.gson.annotations.SerializedName

data class MovieDetailApiResponse(

    @SerializedName("Poster")
    var poster: String,
    
    @SerializedName("Language")
    var language: String,
    
    @SerializedName("Plot")
    var plot: String,
    
    @SerializedName("Actors")
    var actors: String,

    @SerializedName("Year")
    var year: String,
    
    @SerializedName("Title")
    var title: String
)