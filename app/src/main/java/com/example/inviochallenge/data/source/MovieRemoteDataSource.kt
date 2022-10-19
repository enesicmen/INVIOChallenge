package com.example.inviochallenge.data.source

import com.example.inviochallenge.data.api.ApiService
import com.example.inviochallenge.di.qualifier.MovieDataSourceLocal
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@OptIn(DelicateCoroutinesApi::class)
class MovieRemoteDataSource
@Inject constructor(
    private val apiService: ApiService,
    @MovieDataSourceLocal private val movieLocalDataSource: MovieDataSource
):MovieDataSource{

}