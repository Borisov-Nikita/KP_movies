package nik.borisov.kpmovies.domain.usecases

import nik.borisov.kpmovies.data.MovieType
import nik.borisov.kpmovies.domain.entities.MoviePreview
import nik.borisov.kpmovies.domain.repositories.Repository
import nik.borisov.kpmovies.utils.DataResult

class GetMoviesPreviewUseCase(
    private val repository: Repository
) {

    suspend fun getMoviesPreview(type: MovieType, limit: Int): DataResult<List<MoviePreview>> {
        return repository.getMoviesPreview(type, limit)
    }
}