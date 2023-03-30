package nik.borisov.kpmovies.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nik.borisov.kpmovies.data.RepositoryImpl
import nik.borisov.kpmovies.domain.entities.Review
import nik.borisov.kpmovies.domain.usecases.GetReviewsUseCase
import nik.borisov.kpmovies.utils.DataResult

class ReviewListViewModel : ViewModel() {

    private val repository = RepositoryImpl()

    private val getReviewsUseCase = GetReviewsUseCase(repository)

    private val _reviews = MutableLiveData<DataResult<List<Review>>>()
    val reviews: LiveData<DataResult<List<Review>>>
        get() = _reviews

    private val reviewList = mutableListOf<Review>()

    private var page = 1

    fun getReviews(movieId: Int) {
        viewModelScope.launch {
            val result = getReviewsUseCase.getReviews(movieId, page++)
            if (result is DataResult.Success && result.data != null) {
                reviewList.addAll(result.data)
                _reviews.value = DataResult.Success(data = reviewList.toList())
            } else {
                _reviews.value = result
            }
        }
    }
}