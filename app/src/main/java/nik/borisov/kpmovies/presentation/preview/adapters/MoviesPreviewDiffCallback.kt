package nik.borisov.kpmovies.presentation.preview.adapters

import androidx.recyclerview.widget.DiffUtil
import nik.borisov.kpmovies.domain.entities.MoviePreview

class MoviesPreviewDiffCallback : DiffUtil.ItemCallback<MoviePreview>() {

    override fun areItemsTheSame(oldItem: MoviePreview, newItem: MoviePreview): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MoviePreview, newItem: MoviePreview): Boolean {
        return oldItem == newItem
    }
}