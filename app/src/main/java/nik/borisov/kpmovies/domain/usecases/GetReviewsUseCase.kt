package nik.borisov.kpmovies.domain.usecases

import nik.borisov.kpmovies.domain.entities.Review
import nik.borisov.kpmovies.domain.repositories.Repository

class GetReviewsUseCase(
    private val repository: Repository
) {

    suspend fun getReviews(movieId: Int, page: Int): List<Review> {
        return repository.getReviews(movieId, page)
    }
}