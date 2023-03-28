package nik.borisov.kpmovies.presentation.adapters

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import nik.borisov.kpmovies.R
import nik.borisov.kpmovies.domain.ReviewType
import java.text.SimpleDateFormat
import java.util.*


fun setupRating(textView: TextView, rating: Double) {
    val backgroundId = if (rating >= 7) {
        R.drawable.rating_green
    } else if (rating < 5) {
        R.drawable.rating_red
    } else {
        R.drawable.rating_gray
    }
    val background = ContextCompat.getDrawable(textView.context, backgroundId)
    textView.background = background
    val ratingText = "%.1f".format(rating)
    textView.text = ratingText
    textView.visibility = TextView.VISIBLE
}

fun setupReviewType(view: View, type: ReviewType) {
    val backgroundId = when (type) {
        ReviewType.TYPE_POSITIVE -> {
            ContextCompat.getColor(view.context, android.R.color.holo_green_light)
        }
        ReviewType.TYPE_NEGATIVE -> {
            ContextCompat.getColor(view.context, android.R.color.holo_red_light)
        }
        ReviewType.TYPE_NEUTRAL -> {
            ContextCompat.getColor(view.context, android.R.color.darker_gray)
        }
    }
    view.setBackgroundColor(backgroundId)
}

fun setupReviewDate(textView: TextView, date: String) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    val outputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)
    val input = inputFormat.parse(date)
    val output = outputFormat.format(input)
    textView.text = output
}