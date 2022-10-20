package com.example.inviochallenge.ui.movieListDetail

import androidx.lifecycle.ViewModel
import com.example.inviochallenge.data.DataCallback
import com.example.inviochallenge.data.Resource
import com.example.inviochallenge.data.api.response.MovieDetailApiResponse
import com.example.inviochallenge.data.repository.MoviesRepository
import com.example.inviochallenge.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
): ViewModel(){

    var movieDetail: SingleLiveEvent<Resource<MovieDetailApiResponse>> = SingleLiveEvent()

    fun getMovieDetail(movieId: String) {
        movieDetail.postValue(Resource.Loading())
        moviesRepository.getMovieDetail(movieId,object : DataCallback<MovieDetailApiResponse> {
            override fun onSuccess(data: MovieDetailApiResponse) {
                movieDetail.postValue(Resource.Success(data))
            }
            override fun onError(message: String) {
                movieDetail.postValue(Resource.Error(message))
            }
        })
    }
}