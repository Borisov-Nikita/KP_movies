package nik.borisov.kpmovies.domain.usecases

import nik.borisov.kpmovies.domain.entities.Movie
import nik.borisov.kpmovies.domain.repositories.Repository
import nik.borisov.kpmovies.utils.DataResult

class GetMovieUseCase(
    private val repository: Repository
) {

    suspend fun getMovie(movieId: Int): DataResult<Movie> {
        return repository.getMovie(movieId)
    }
}