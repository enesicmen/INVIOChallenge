package com.example.inviochallenge.ui.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inviochallenge.data.Resource
import com.example.inviochallenge.data.model.Movie
import com.example.inviochallenge.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val moviesRepository: MovieRepository
): ViewModel() {

    private val _movieListLiveData = MutableLiveData<Resource<List<Movie>>>()
    val movieListLiveData: LiveData<Resource<List<Movie>>> = _movieListLiveData

    fun searchMovies(searchText: String) {
        _movieListLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            _movieListLiveData.postValue(moviesRepository.getMovieList(searchText))
        }
    }
}