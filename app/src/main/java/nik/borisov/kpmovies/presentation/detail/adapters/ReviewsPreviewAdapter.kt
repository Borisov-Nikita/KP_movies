package nik.borisov.kpmovies.presentation.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import nik.borisov.kpmovies.databinding.ReviewItemBinding
import nik.borisov.kpmovies.domain.entities.Review
import nik.borisov.kpmovies.presentation.setupReviewType

class ReviewsPreviewAdapter : ListAdapter<Review, ReviewsViewHolder>(ReviewsDiffCallback()) {

    var onReviewClickListener: ((Review) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val binding = ReviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ReviewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        val review = getItem(position)
        with(holder.binding) {
            setupReviewType(ivType, review.type)
            tvDate.text = review.date
            tvAuthor.text = review.author
            tvReview.text = review.review
            cvReviewItem.setOnClickListener {
                onReviewClickListener?.invoke(review)
            }
        }
    }
}