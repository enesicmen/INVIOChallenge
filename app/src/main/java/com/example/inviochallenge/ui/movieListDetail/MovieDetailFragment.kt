package com.example.inviochallenge.ui.movieListDetail

import android.os.Bundle
import android.view.View
import com.example.inviochallenge.data.Resource
import com.example.inviochallenge.data.api.response.MovieDetailApiResponse
import com.example.inviochallenge.databinding.FragmentMovieDetailBinding
import com.example.inviochallenge.ui.common.BaseFragment
import com.example.inviochallenge.ui.common.ext.load
import com.example.inviochallenge.ui.common.ext.setVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment:
      BaseFragment<FragmentMovieDetailBinding,MovieDetailViewModel>(){

    private var mMovieId: String? = null

    private var mMovieDetail: MovieDetailApiResponse? = null

    override fun setViewModelClass() = MovieDetailViewModel::class.java

    override fun setViewBinding(): FragmentMovieDetailBinding =
        FragmentMovieDetailBinding.inflate(layoutInflater)

    override fun initView(savedInstanceState: Bundle?) {
        clickOnBack()
        getViewModel()?.movieDetail?.observe(this) {
            when(it) {
                is Resource.Loading -> getViewBinding()?.progressBar?.setVisibility(isVisible = true)

                is Resource.Success -> {
                    getViewBinding()?.progressBar?.setVisibility(isVisible = false)
                    mMovieDetail = it.data
                    setData()
                }
                is Resource.Error -> getViewBinding()?.progressBar?.setVisibility(isVisible = false)
            }
        }
    }

    override fun readDataFromArguments() {
        super.readDataFromArguments()
        arguments?.let {
            val safeArgs = MovieDetailFragmentArgs.fromBundle(it)
            mMovieId = safeArgs.movieId
        }
    }

    override fun initLogic() {
        super.initLogic()
        getViewModel()!!.getMovieDetail(movieId = mMovieId!!)
    }

    private fun setData(){
        getViewBinding()?.apply {
            ivImage.load(mMovieDetail?.poster)
            tvTitle.text = mMovieDetail?.title
            tvYear.text = mMovieDetail?.year
            tvLanguage.text = mMovieDetail?.language
            tvActors.text = mMovieDetail?.actors
            tvDescription.text = mMovieDetail?.plot
            tvMovieName.text = mMovieDetail?.title
        }
    }

    private fun clickOnBack() {
        getViewBinding()?.backButton?.setOnClickListener(View.OnClickListener {
            requireActivity().onBackPressed()
        })
    }
}