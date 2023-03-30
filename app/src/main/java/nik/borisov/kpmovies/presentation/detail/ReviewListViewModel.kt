package nik.borisov.kpmovies.presentation.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nik.borisov.kpmovies.data.RepositoryImpl
import nik.borisov.kpmovies.domain.entities.Review
import nik.borisov.kpmovies.domain.usecases.GetReviewsUseCase

class ReviewListViewModel : ViewModel() {

    private val repository = RepositoryImpl()

    private val getReviewsUseCase = GetReviewsUseCase(repository)

    private val _reviews = MutableLiveData<List<Review>>()
    val reviews: LiveData<List<Review>>
        get() = _reviews

    private val reviewList = mutableListOf<Review>()

    private var page = 1

    fun getReviews(movieId: Int) {
        viewModelScope.launch {
            reviewList.addAll(getReviewsUseCase.getReviews(movieId, page++))
            for (review in reviewList) {
                Log.d("Test", review.author)
            }
            _reviews.value = reviewList.toList()
        }
    }
}