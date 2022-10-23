package com.example.inviochallenge.ui.movieListDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inviochallenge.data.Resource
import com.example.inviochallenge.data.model.Movie
import com.example.inviochallenge.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
): ViewModel(){

    private val _movieDetailsLiveData = MutableLiveData<Resource<Movie>>()
    val movieDetailsLiveData: LiveData<Resource<Movie>> = _movieDetailsLiveData

    fun getMovieDetail(movieId: String) {
        _movieDetailsLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            _movieDetailsLiveData.postValue(moviesRepository.getMovieDetail(movieId))
        }
    }
}