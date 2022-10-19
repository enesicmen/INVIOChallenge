package com.example.inviochallenge.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MovieDataSourceLocal

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MovieDataSourceRemote
