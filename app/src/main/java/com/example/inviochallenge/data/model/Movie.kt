package com.example.inviochallenge.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Movie (

    @PrimaryKey
    @SerializedName("ID")
    val id: Int
 )