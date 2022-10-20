package com.example.inviochallenge.ui.movieList

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.inviochallenge.data.Resource
import com.example.inviochallenge.data.model.Movie
import com.example.inviochallenge.databinding.FragmentMoviesBinding
import com.example.inviochallenge.ui.common.BaseFragment
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
                is Resource.Success -> {
                    //getViewBinding()?.progressBar?.setVisibility(isVisible = false)
                    Log.i("enes",it.data.toString())
                    setMovieList(it.data!!)
                }
                is Resource.Error -> {
                    //getViewBinding()?.progressBar?.setVisibility(isVisible = false)
                    //showErrorViews()
                }
            }
        }
    }

    override fun initLogic() {
        super.initLogic()

        getViewBinding()?.searchView?.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
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
        getViewBinding()?.rvSearchMovie?.layoutManager = GridLayoutManager(activity, 3)
    }

    private fun setMovieList(movies: List<Movie>) {
        mMoviesList.clear()
        mMoviesList.addAll(movies)
        mMoviesAdapter.notifyDataSetChanged()

    }
}