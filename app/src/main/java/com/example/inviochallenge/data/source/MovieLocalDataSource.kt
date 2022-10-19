package com.example.inviochallenge.data.source

import com.example.inviochallenge.data.local.MovieDao
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@OptIn(DelicateCoroutinesApi::class)
class MovieLocalDataSource
@Inject constructor(
    private val movieDao: MovieDao
):MovieDataSource{

}