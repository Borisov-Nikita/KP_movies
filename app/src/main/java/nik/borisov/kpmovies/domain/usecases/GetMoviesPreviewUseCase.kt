package nik.borisov.kpmovies.domain.usecases

import nik.borisov.kpmovies.data.MovieType
import nik.borisov.kpmovies.domain.entities.MoviePreview
import nik.borisov.kpmovies.domain.repositories.Repository

class GetMoviesPreviewUseCase(
    private val repository: Repository
) {

    suspend fun getMoviesPreview(type: MovieType, limit: Int): List<MoviePreview> {
        return repository.getMoviesPreview(type, limit)
    }
}