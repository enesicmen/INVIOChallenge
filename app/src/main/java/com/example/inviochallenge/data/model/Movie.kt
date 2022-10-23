package com.example.inviochallenge.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Movie (

    @PrimaryKey
    @SerializedName("imdbID")
    val movieId: String,

    @SerializedName("Title")
    val title: String,

    @SerializedName("Year")
    val year: String,

    @SerializedName("Type")
    val type: String,

    @SerializedName("Poster")
    val movieImage: String,

    @SerializedName("Language")
    var language: String? = "",

    @SerializedName("Plot")
    var plot: String? = "",

    @SerializedName("Actors")
    var actors: String? = "",
)