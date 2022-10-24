package com.example.inviochallenge.ui.movieList

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.inviochallenge.R
import com.example.inviochallenge.data.Resource
import com.example.inviochallenge.data.model.Movie
import com.example.inviochallenge.databinding.FragmentMovieListBinding
import com.example.inviochallenge.ui.MainActivity
import com.example.inviochallenge.ui.common.BaseFragment
import com.example.inviochallenge.ui.common.ext.setVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment:
      BaseFragment<FragmentMovieListBinding,MovieListViewModel>(){

    lateinit var mMovieAdapter: MovieAdapter

    private var mMoviesList: MutableList<Movie> = mutableListOf()

    override fun initView(savedInstanceState: Bundle?) {
        initMoviesAdapter()
        setToolbar(title = getString(R.string.movies), toolbar = getViewBinding()?.toolBar)
        getViewBinding()?.searchView?.isIconified = false
    }

    override fun initObservers() {
        super.initObservers()
        getViewModel()?.movieListLiveData?.observe(this){
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
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun setViewModelClass() = MovieListViewModel::class.java

    override fun setViewBinding(): FragmentMovieListBinding =
        FragmentMovieListBinding.inflate(layoutInflater)

    private fun initMoviesAdapter() {
        mMovieAdapter = MovieAdapter(
            mMovieList = mMoviesList,
            onClicked = {
                val actionDetail = MovieListFragmentDirections.actionMoviesFragmentToMovieDetail(movieId = mMoviesList[it].movieId ?: "")
                findNavController().navigate(actionDetail)
            }
        )
        getViewBinding()?.rvSearchMovie?.adapter = mMovieAdapter
    }
    private fun setMovieList(movies: List<Movie>) {
        mMoviesList.clear()
        mMoviesList.addAll(movies)
        mMovieAdapter.notifyDataSetChanged()
        if(movies.isNotEmpty()){
            getViewBinding()?.rvSearchMovie.setVisibility(isVisible = true)
            getViewBinding()?.tvDataNotFound.setVisibility(isVisible = false)
        }else{
            getViewBinding()?.rvSearchMovie.setVisibility(isVisible = false)
            getViewBinding()?.tvDataNotFound.setVisibility(isVisible = true)
        }
    }
}