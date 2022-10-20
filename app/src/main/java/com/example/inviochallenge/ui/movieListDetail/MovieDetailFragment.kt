package com.example.inviochallenge.ui.movieListDetail

import android.os.Bundle
import android.view.View
import com.example.inviochallenge.data.Resource
import com.example.inviochallenge.data.api.response.MovieDetailApiResponse
import com.example.inviochallenge.databinding.FragmentMovieDetailBinding
import com.example.inviochallenge.ui.common.BaseFragment
import com.example.inviochallenge.ui.common.ext.load
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
        onBackPressed()
        getViewModel()?.movieDetail?.observe(this) {
            when(it) {
                is Resource.Success -> {
                    mMovieDetail = it.data
                    setData()
                }
                is Resource.Error -> {

                }
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

    private fun onBackPressed(){
        getViewBinding()?.btnBack?.setOnClickListener(View.OnClickListener {

        })
    }

    private fun setData(){
        getViewBinding()?.apply {
            tvRatings.text = mMovieDetail?.imdbrating
            tvPlot.text = mMovieDetail?.plot
            tvGenre.text = mMovieDetail?.genre
            tvLanguage.text = mMovieDetail?.language
            tvYear2.text = mMovieDetail?.year?.substring(0, 4)
            tvRatings2.text = mMovieDetail?.metascore
            tvStars.text = mMovieDetail?.actors
            tvWriter.text = mMovieDetail?.writer
            tvDirector.text = mMovieDetail?.director
            tvBoxoffice.text = mMovieDetail?.boxoffice
            ivDetailIamge.load(mMovieDetail?.poster)
            ivDetailSmallImage.load(mMovieDetail?.poster)
        }

    }
}