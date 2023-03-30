package nik.borisov.kpmovies.presentation.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import nik.borisov.kpmovies.databinding.TrailerItemBinding
import nik.borisov.kpmovies.domain.entities.Trailer

class TrailersAdapter : ListAdapter<Trailer, TrailersViewHolder>(TrailersDiffCallback()) {

    var onTrailerClickListener: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailersViewHolder {
        val binding = TrailerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TrailersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrailersViewHolder, position: Int) {
        val trailer = currentList[position]
        holder.binding.tvTrailerName.text = trailer.name
        holder.binding.cvTrailer.setOnClickListener {
            onTrailerClickListener?.invoke(trailer.url)
        }
    }
}