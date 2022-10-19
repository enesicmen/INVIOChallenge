package com.example.inviochallenge.data.source

import com.example.inviochallenge.data.DataCallback
import com.example.inviochallenge.data.model.Movie

interface MovieDataSource {

    fun searchMovies(searchText: String, callback: DataCallback<List<Movie>>)

    fun saveMovieList(movieList: List<Movie>)
}