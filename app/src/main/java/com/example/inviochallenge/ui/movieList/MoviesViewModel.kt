package com.example.inviochallenge.ui.movieList

import androidx.lifecycle.ViewModel
import com.example.inviochallenge.data.DataCallback
import com.example.inviochallenge.data.Resource
import com.example.inviochallenge.data.model.Movie
import com.example.inviochallenge.data.repository.MoviesRepository
import com.example.inviochallenge.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
): ViewModel() {

    var movieList: SingleLiveEvent<Resource<List<Movie>>> = SingleLiveEvent()

    fun searchMovies(searchText: String) {
        movieList.postValue(Resource.Loading())
        moviesRepository.getMovieList(searchText,object : DataCallback<List<Movie>> {
            override fun onSuccess(data: List<Movie>) {
                movieList.postValue(Resource.Success(data))
            }
            override fun onError(message: String) {
                movieList.postValue(Resource.Error(message))
            }
        })
    }
}