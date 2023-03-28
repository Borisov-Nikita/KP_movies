package nik.borisov.kpmovies.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import nik.borisov.kpmovies.domain.entities.Trailer

class TrailersDiffCallback: DiffUtil.ItemCallback<Trailer>() {

    override fun areItemsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem == newItem
    }
}