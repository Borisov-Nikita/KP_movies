package nik.borisov.kpmovies.presentation.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import nik.borisov.kpmovies.databinding.FragmentReviewBinding
import nik.borisov.kpmovies.domain.entities.Review
import nik.borisov.kpmovies.presentation.adapters.setupReviewDate
import nik.borisov.kpmovies.presentation.adapters.setupReviewType

class ReviewFragment : Fragment() {

    private var _binding: FragmentReviewBinding? = null
    private val binding: FragmentReviewBinding
        get() = _binding ?: throw NullPointerException("FragmentMovieDetailBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val review = parseArgs()
        with(binding) {
            setupReviewType(ivType, review.type)
            setupReviewDate(tvDate, review.date)
            tvAuthor.text = review.author
            tvReview.text = if (review.title != "") {
                "%s\n\n%s".format(review.title, review.review)
            } else {
                "%s".format(review.review)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun parseArgs(): Review {
        if (!requireArguments().containsKey(ARG_REVIEW)) {
            throw IllegalArgumentException("Argument review is absent.")
        }
        val review = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getSerializable(ARG_REVIEW, Review::class.java)
        } else {
            @Suppress("DEPRECATION")
            requireArguments().getSerializable(ARG_REVIEW) as? Review
        }
        return review ?: throw IllegalArgumentException("Review is null")
    }

    companion object {

        private const val ARG_REVIEW = "review"

        fun newInstance(review: Review) = ReviewFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARG_REVIEW, review)
            }
        }
    }
}