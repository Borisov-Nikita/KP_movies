package nik.borisov.kpmovies.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import nik.borisov.kpmovies.databinding.ReviewItemBinding
import nik.borisov.kpmovies.domain.entities.Review

class ReviewsAdapter : ListAdapter<Review, ReviewsViewHolder>(ReviewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val binding = ReviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ReviewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        val review = currentList[position]
        with(holder.binding) {
            setupReviewType(vType, review.type)
            setupReviewDate(tvDate, review.date)
            tvAuthor.text = review.author
            tvReview.text = if(review.title != "") {
                "%s\n\n%s".format(review.title, review.review)
            } else  {
                "%s".format(review.review)
            }

        }
    }
}