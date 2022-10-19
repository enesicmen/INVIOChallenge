package com.example.inviochallenge.ui.movieList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inviochallenge.data.model.Movie
import com.example.inviochallenge.databinding.RowMoviesBinding
import com.example.inviochallenge.ui.common.RecyclerItemClickListener

class MoviesAdapter(
    private val mMovieList: MutableList<Movie>,
    private val onClicked: RecyclerItemClickListener
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder> (){

    class ViewHolder(
        private val binding: RowMoviesBinding,
        private val onClicked: RecyclerItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(
                viewGroup: ViewGroup,
                onClicked: RecyclerItemClickListener,
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(viewGroup.context)
                val binding = RowMoviesBinding.inflate(layoutInflater, viewGroup, false)
                return ViewHolder(binding = binding, onClicked = onClicked)
            }
        }

        init {
            itemView.setOnClickListener { onClicked(adapterPosition) }
        }

        fun bind(item: Movie) {
            binding.apply {
                title.text = item.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(viewGroup = parent, onClicked = onClicked)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(item = mMovieList[position])

    override fun getItemCount(): Int =
        mMovieList.size
}