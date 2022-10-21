package com.example.inviochallenge.ui.movieList

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.inviochallenge.data.Resource
import com.example.inviochallenge.data.model.Movie
import com.example.inviochallenge.databinding.FragmentMoviesBinding
import com.example.inviochallenge.ui.common.BaseFragment
import com.example.inviochallenge.ui.common.ext.setVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment:
      BaseFragment<FragmentMoviesBinding,MoviesViewModel>(){

    lateinit var mMoviesAdapter: MoviesAdapter

    private var mMoviesList: MutableList<Movie> = mutableListOf()

    override fun initView(savedInstanceState: Bundle?) {
        initMoviesAdapter()
        getViewModel()?.movieList?.observe(this){
            when (it) {
                is Resource.Loading ->getViewBinding()?.progressBar?.setVisibility(isVisible = true)
                is Resource.Success -> {
                    getViewBinding()?.progressBar?.setVisibility(isVisible = false)
                    setMovieList(it.data!!)
                }
                is Resource.Error -> getViewBinding()?.progressBar?.setVisibility(isVisible = false)
            }
        }
    }
    override fun initLogic() {
        super.initLogic()

        getViewBinding()?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){
                    getViewBinding()?.rvSearchMovie?.scrollToPosition(0)
                    getViewModel()?.searchMovies(query)
                    getViewBinding()?.searchView?.clearFocus()
                }else{
                    Toast.makeText(context, "Text yazılmalı", Toast.LENGTH_SHORT).show()
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }


    override fun setViewModelClass() = MoviesViewModel::class.java

    override fun setViewBinding(): FragmentMoviesBinding =
        FragmentMoviesBinding.inflate(layoutInflater)

    private fun initMoviesAdapter() {
        mMoviesAdapter = MoviesAdapter(
            mMovieList = mMoviesList,
            onClicked = {
                val actionDetail = MoviesFragmentDirections.actionMoviesFragmentToMovieDetail(movieId = mMoviesList[it].movieId ?: "")
                findNavController().navigate(actionDetail)
            }
        )
        getViewBinding()?.rvSearchMovie?.adapter = mMoviesAdapter
    }
    private fun setMovieList(movies: List<Movie>) {
        mMoviesList.clear()
        mMoviesList.addAll(movies)
        mMoviesAdapter.notifyDataSetChanged()
        if(movies.isNotEmpty()){
            getViewBinding()?.rvSearchMovie.setVisibility(isVisible = true)
            getViewBinding()?.tvDataNotFound.setVisibility(isVisible = false)
        }else{
            getViewBinding()?.rvSearchMovie.setVisibility(isVisible = false)
            getViewBinding()?.tvDataNotFound.setVisibility(isVisible = true)
        }
    }
}