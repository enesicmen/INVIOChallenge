package com.example.inviochallenge.ui.movieList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inviochallenge.data.model.Movie
import com.example.inviochallenge.databinding.RowMovieListBinding
import com.example.inviochallenge.ui.common.RecyclerItemClickListener
import com.example.inviochallenge.ui.common.ext.load

class MovieAdapter(
    private val mMovieList: MutableList<Movie>,
    private val onClicked: RecyclerItemClickListener
) : RecyclerView.Adapter<MovieAdapter.ViewHolder> (){

    class ViewHolder(
        private val binding: RowMovieListBinding,
        private val onClicked: RecyclerItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(
                viewGroup: ViewGroup,
                onClicked: RecyclerItemClickListener,
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(viewGroup.context)
                val binding = RowMovieListBinding.inflate(layoutInflater, viewGroup, false)
                return ViewHolder(binding = binding, onClicked = onClicked)
            }
        }

        init {
            itemView.setOnClickListener { onClicked(adapterPosition) }
        }

        fun bind(item: Movie) {
            binding.apply {
                tvTitle.text = item.title
                ivImage.load(item.movieImage)
                tvYear.text = item.year
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