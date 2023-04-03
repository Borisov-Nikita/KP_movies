package nik.borisov.kpmovies.domain.usecases

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import nik.borisov.kpmovies.domain.MovieType
import nik.borisov.kpmovies.domain.entities.MoviePreview
import nik.borisov.kpmovies.domain.repositories.Repository

class GetMoviesPreviewUseCase(
    private val repository: Repository
) {

    fun getMoviesPreview(type: MovieType, limit: Int): Flow<PagingData<MoviePreview>> {
        return repository.getMoviesPreview(type, limit)
    }
}