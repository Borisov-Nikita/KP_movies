package nik.borisov.kpmovies.presentation.preview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.bumptech.glide.Glide
import nik.borisov.kpmovies.databinding.MoviePreviewItemBinding
import nik.borisov.kpmovies.domain.entities.MoviePreview
import nik.borisov.kpmovies.presentation.setupRating

class MoviesPreviewAdapter :
    PagingDataAdapter<MoviePreview, MoviesPreviewViewHolder>(MoviesPreviewDiffCallback()) {

    var onMoviePreviewClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesPreviewViewHolder {
        val binding = MoviePreviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MoviesPreviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesPreviewViewHolder, position: Int) {
        val moviePreview = getItem(position)
        if (moviePreview != null) {
            val poster = moviePreview.poster
            Glide.with(holder.itemView)
                .load(poster)
                .into(holder.binding.ivPoster)
            val rating = moviePreview.rating
            setupRating(holder.binding.tvRatingKp, rating)
            holder.binding.cvMoviePreview.setOnClickListener {
                onMoviePreviewClickListener?.invoke(moviePreview.id)
            }
        }
    }
}