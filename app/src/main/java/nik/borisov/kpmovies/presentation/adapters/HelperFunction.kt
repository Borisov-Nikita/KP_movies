package nik.borisov.kpmovies.presentation.adapters

import android.widget.TextView
import androidx.core.content.ContextCompat
import nik.borisov.kpmovies.R


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