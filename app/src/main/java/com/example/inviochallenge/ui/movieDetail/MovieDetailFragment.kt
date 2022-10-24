package com.example.inviochallenge.ui.movieDetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
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
        getViewBinding()?.toolBar?.setNavigationOnClickListener(View.OnClickListener {
            requireActivity().onBackPressed()
        })
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
            mMovieDetail?.let {
                ivImage.load(it.movieImage)
                tvTitle.text = it.title
                tvYear.text = it.year
                tvLanguage.text = it.language
                tvActors.text = it.actors
                tvDescription.text = it.plot
                setToolbar(title = it.title, toolbar = toolBar)
            }
        }
    }
}