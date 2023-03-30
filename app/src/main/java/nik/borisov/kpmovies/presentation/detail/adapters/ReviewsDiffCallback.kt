package nik.borisov.kpmovies.presentation.detail.adapters

import androidx.recyclerview.widget.DiffUtil
import nik.borisov.kpmovies.domain.entities.Review

class ReviewsDiffCallback : DiffUtil.ItemCallback<Review>() {

    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }
}