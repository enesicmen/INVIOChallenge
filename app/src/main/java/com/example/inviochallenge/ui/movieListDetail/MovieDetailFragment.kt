package com.example.inviochallenge.ui.movieListDetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.inviochallenge.data.Resource
import com.example.inviochallenge.data.model.Movie
import com.example.inviochallenge.databinding.FragmentMovieDetailBinding
import com.example.inviochallenge.ui.common.BaseFragment
import com.example.inviochallenge.ui.common.ext.load
import com.example.inviochallenge.ui.common.ext.setVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment:
    BaseFragment<FragmentMovieDetailBinding,MovieDetailViewModel>(){

    private var mMovieId: String? = null

    private var mMovieDetail: Movie? = null

    override fun setViewModelClass() = MovieDetailViewModel::class.java

    override fun setViewBinding(): FragmentMovieDetailBinding =
        FragmentMovieDetailBinding.inflate(layoutInflater)

    override fun readDataFromArguments() {
        super.readDataFromArguments()
        arguments?.let {
            val safeArgs = MovieDetailFragmentArgs.fromBundle(it)
            mMovieId = safeArgs.movieId
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        clickOnBack()
    }

    override fun initObservers() {
        super.initObservers()
        getViewModel()?.movieDetailsLiveData?.observe(this) {
            when(it) {
                is Resource.Loading -> getViewBinding()?.progressBar?.setVisibility(isVisible = true)

                is Resource.Success -> {
                    getViewBinding()?.progressBar?.setVisibility(isVisible = false)
                    mMovieDetail = it.data
                    setData()
                }
                is Resource.Error -> {
                    getViewBinding()?.progressBar?.setVisibility(isVisible = false)
                    Toast.makeText(context, "Error: ${it.error}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun initLogic() {
        super.initLogic()
        getViewModel()!!.getMovieDetail(movieId = mMovieId!!)
    }

    private fun setData(){
        getViewBinding()?.apply {
            ivImage.load(mMovieDetail?.movieImage)
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