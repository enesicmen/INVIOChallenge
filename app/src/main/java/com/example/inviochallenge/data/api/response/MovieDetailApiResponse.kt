package com.example.inviochallenge.data.api.response

import com.google.gson.annotations.SerializedName

data class MovieDetailApiResponse(

    @SerializedName("BoxOffice")
    var boxoffice: String,

    @SerializedName("imdbRating")
    var imdbrating: String,

    @SerializedName("Metascore")
    var metascore: String,

    @SerializedName("Poster")
    var poster: String,
    
    @SerializedName("Language")
    var language: String,
    
    @SerializedName("Plot")
    var plot: String,
    
    @SerializedName("Actors")
    var actors: String,
    
    @SerializedName("Writer")
    var writer: String,

    @SerializedName("Director")
    var director: String,

    @SerializedName("Genre")
    var genre: String,

    @SerializedName("Year")
    var year: String,
    
    @SerializedName("Title")
    var title: String
)