package com.alberto.themoviedb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alberto.themoviedb.BuildConfig
import com.alberto.themoviedb.R
import com.alberto.themoviedb.data.models.Domain
import com.alberto.themoviedb.databinding.MovieItemBinding
import com.alberto.themoviedb.helper.extensions.loadImage

internal class MovieAdapter(private val onMovieClicked: (movie: Domain.Movie) -> Unit): ListAdapter<Domain.Movie, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (holder is MovieViewHolder) {
            holder.bind(item, onMovieClicked)
        }
    }

    private class MovieViewHolder(private val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Domain.Movie, onMovieClicked: (movie: Domain.Movie) -> Unit) {
            binding.title.text = movie.title
            binding.overview.text = movie.overview
            binding.image.loadImage( movie.imageUrl)
            binding.container.setOnClickListener { onMovieClicked(movie) }

            val aboveThreshold = movie.voteAverage > 8
            if (aboveThreshold) {
                binding.container.strokeColor = ContextCompat.getColor(binding.root.context, R.color.purple_200)
                binding.container.strokeWidth = 5
                binding.container.invalidate()
            } else {
                binding.container.strokeColor = ContextCompat.getColor(binding.root.context, R.color.white)
                binding.container.strokeWidth = 0
                binding.container.invalidate()
            }
        }

        companion object {
            fun from(parent: ViewGroup): MovieViewHolder {
                val view = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return MovieViewHolder(view)
            }
        }
    }

    private class DiffCallback: DiffUtil.ItemCallback<Domain.Movie>() {
        override fun areItemsTheSame(oldItem: Domain.Movie, newItem: Domain.Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Domain.Movie, newItem: Domain.Movie): Boolean {
           return oldItem == newItem
        }
    }
}