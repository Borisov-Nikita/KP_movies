package nik.borisov.kpmovies.domain.usecases

import nik.borisov.kpmovies.domain.entities.Review
import nik.borisov.kpmovies.domain.repositories.Repository

class GetReviewsUseCase(
    val repository: Repository
) {

    suspend fun getReviews(movieId: Int): List<Review> {
        return repository.getReviews(movieId)
    }
}