package com.example.inviochallenge.ui.movieListDetail

import androidx.lifecycle.ViewModel
import com.example.inviochallenge.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
): ViewModel(){
}