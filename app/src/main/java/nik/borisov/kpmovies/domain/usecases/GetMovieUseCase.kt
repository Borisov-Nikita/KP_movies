package nik.borisov.kpmovies.domain.usecases

import nik.borisov.kpmovies.domain.entities.Movie
import nik.borisov.kpmovies.domain.repositories.Repository

class GetMovieUseCase(
    private val repository: Repository
) {

    suspend fun getMovie(movieId: Int): Movie {
        return repository.getMovie(movieId)
    }
}