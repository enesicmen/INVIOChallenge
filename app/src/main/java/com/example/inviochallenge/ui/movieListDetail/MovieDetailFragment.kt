package com.example.inviochallenge.ui.movieListDetail

import android.os.Bundle
import com.example.inviochallenge.databinding.FragmentMovieDetailBinding
import com.example.inviochallenge.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment:
      BaseFragment<FragmentMovieDetailBinding,MovieDetailViewModel>(){
    override fun initView(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun setViewModelClass() = MovieDetailViewModel::class.java

    override fun setViewBinding(): FragmentMovieDetailBinding =
        FragmentMovieDetailBinding.inflate(layoutInflater)
}