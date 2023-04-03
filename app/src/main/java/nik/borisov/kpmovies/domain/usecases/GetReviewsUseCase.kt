package nik.borisov.kpmovies.domain.usecases

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import nik.borisov.kpmovies.domain.entities.Review
import nik.borisov.kpmovies.domain.repositories.Repository

class GetReviewsUseCase(
    private val repository: Repository
) {

    fun getReviews(movieId: Int): Flow<PagingData<Review>> {
        return repository.getReviews(movieId)
    }
}