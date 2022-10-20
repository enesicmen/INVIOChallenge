package com.example.inviochallenge.data.source

import com.example.inviochallenge.data.DataCallback
import com.example.inviochallenge.data.api.ApiService
import com.example.inviochallenge.data.api.response.MovieDetailApiResponse
import com.example.inviochallenge.data.api.response.SearchApiResponse
import com.example.inviochallenge.data.model.Movie
import com.example.inviochallenge.di.qualifier.MovieDataSourceLocal
import kotlinx.coroutines.DelicateCoroutinesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@OptIn(DelicateCoroutinesApi::class)
class MovieRemoteDataSource
@Inject constructor(
    private val apiService: ApiService,
    @MovieDataSourceLocal private val movieLocalDataSource: MovieDataSource
):MovieDataSource{
    override fun searchMovies(searchText: String, callback: DataCallback<List<Movie>>) {

        val call: Call<SearchApiResponse> = apiService.searchMovies(searchText)
        call.enqueue(object : Callback<SearchApiResponse> {
            override fun onResponse(
                call: Call<SearchApiResponse>,
                response: Response<SearchApiResponse>
            ) {
                if (response.isSuccessful) {
                    val movieList = response.body()!!.movies
                    //val movieList = response.body.search()!!
                    movieLocalDataSource.saveMovieList(movieList)
                    callback.onSuccess(data = movieList)
                } else {
                    callback.onError(message = response.message())
                }
            }

            override fun onFailure(call: Call<SearchApiResponse>, t: Throwable) {
                callback.onError(message = t.message ?: "")
            }
        })
    }

    override fun getMovieDetail(movieId: String, callback: DataCallback<MovieDetailApiResponse>) {

        val call: Call<MovieDetailApiResponse> = apiService.getMovieDetail(movieId)
        call.enqueue(object : Callback<MovieDetailApiResponse>{
            override fun onResponse(
                call: Call<MovieDetailApiResponse>,
                response: Response<MovieDetailApiResponse>
            ) {
                if(response.isSuccessful && response.body() != null){
                    callback.onSuccess(data = response.body()!!)
                }else {
                    callback.onError(message = response.message())
                }
            }
            override fun onFailure(call: Call<MovieDetailApiResponse>, t: Throwable) {
                callback.onError(message = t.message ?: "")
            }
        })
    }


    override fun saveMovieList(movieList: List<Movie>) {

    }


}