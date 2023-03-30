package nik.borisov.kpmovies.domain.usecases

import nik.borisov.kpmovies.domain.entities.Review
import nik.borisov.kpmovies.domain.repositories.Repository
import nik.borisov.kpmovies.utils.DataResult

class GetReviewsUseCase(
    private val repository: Repository
) {

    suspend fun getReviews(movieId: Int, page: Int): DataResult<List<Review>> {
        return repository.getReviews(movieId, page)
    }
}