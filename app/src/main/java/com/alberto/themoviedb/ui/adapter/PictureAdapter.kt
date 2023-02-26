package com.alberto.themoviedb.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alberto.themoviedb.data.models.Domain
import com.alberto.themoviedb.databinding.ImageItemBinding
import com.alberto.themoviedb.helper.extensions.loadImage

internal class PictureAdapter(): ListAdapter<Domain.Picture, RecyclerView.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (holder is ImageViewHolder) {
            holder.bind(item)
        }
    }

    private class ImageViewHolder(private val binding: ImageItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Domain.Picture) {
            binding.image.loadImage(movie.imageUrl)
        }

        companion object {
            fun from(parent: ViewGroup): ImageViewHolder {
                val view = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ImageViewHolder(view)
            }
        }
    }

    private class DiffCallback: DiffUtil.ItemCallback<Domain.Picture>() {
        override fun areItemsTheSame(oldItem: Domain.Picture, newItem: Domain.Picture): Boolean {
            return oldItem.imageUrl == newItem.imageUrl
        }

        override fun areContentsTheSame(oldItem: Domain.Picture, newItem: Domain.Picture): Boolean {
            return oldItem == newItem
        }
    }
}