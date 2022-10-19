package com.example.inviochallenge.ui.movieList

import android.os.Bundle
import com.example.inviochallenge.databinding.FragmentMoviesBinding
import com.example.inviochallenge.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment:
      BaseFragment<FragmentMoviesBinding,MoviesViewModel>(){
    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun setViewModelClass() = MoviesViewModel::class.java

    override fun setViewBinding(): FragmentMoviesBinding =
        FragmentMoviesBinding.inflate(layoutInflater)
}