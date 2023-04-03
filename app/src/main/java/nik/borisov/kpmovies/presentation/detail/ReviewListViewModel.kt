package nik.borisov.kpmovies.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import nik.borisov.kpmovies.data.RepositoryImpl
import nik.borisov.kpmovies.domain.entities.Review
import nik.borisov.kpmovies.domain.usecases.GetReviewsUseCase

class ReviewListViewModel : ViewModel() {

    private val repository = RepositoryImpl()

    private val getReviewsUseCase = GetReviewsUseCase(repository)

    fun getReviews(movieId: Int): Flow<PagingData<Review>> {
        return getReviewsUseCase.getReviews(movieId).cachedIn(viewModelScope)
    }
}