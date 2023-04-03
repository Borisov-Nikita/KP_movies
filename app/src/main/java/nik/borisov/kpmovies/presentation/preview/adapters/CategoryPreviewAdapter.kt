package nik.borisov.kpmovies.presentation.preview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.bumptech.glide.Glide
import nik.borisov.kpmovies.databinding.MovieCategoryPreviewItemBinding
import nik.borisov.kpmovies.domain.entities.MoviePreview
import nik.borisov.kpmovies.presentation.setupRating

class CategoryPreviewAdapter :
    PagingDataAdapter<MoviePreview, CategoryPreviewViewHolder>(MoviesPreviewDiffCallback()) {

    var onMoviePreviewClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryPreviewViewHolder {
        val binding = MovieCategoryPreviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryPreviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryPreviewViewHolder, position: Int) {
        val moviePreview = getItem(position)
        if (moviePreview != null) {
            val poster = moviePreview.poster
            Glide.with(holder.itemView)
                .load(poster)
                .into(holder.binding.ivPoster)
            val rating = moviePreview.rating
            with(holder.binding) {
                setupRating(tvRatingKp, rating)
                tvMovieName.text = moviePreview.name
                tvMovieYear.text = moviePreview.year.toString()
                tvMovieGenre.text = moviePreview.genres.toString().trim('[', ']')
                tvMovieCountry.text = moviePreview.countries.toString().trim('[', ']')
                tvMovieLength.text = "%d:%02d".format(
                    moviePreview.movieLength / MIN_IN_HOUR,
                    moviePreview.movieLength % MIN_IN_HOUR
                )
                cvMoviePreview.setOnClickListener {
                    onMoviePreviewClickListener?.invoke(moviePreview.id)
                }
            }
        }
    }

    companion object {

        private const val MIN_IN_HOUR = 60
    }
}