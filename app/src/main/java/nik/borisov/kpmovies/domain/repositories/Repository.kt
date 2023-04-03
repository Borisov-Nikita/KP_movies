package nik.borisov.kpmovies.domain.repositories

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import nik.borisov.kpmovies.data.MovieType
import nik.borisov.kpmovies.domain.entities.Movie
import nik.borisov.kpmovies.domain.entities.MoviePreview
import nik.borisov.kpmovies.domain.entities.Review
import nik.borisov.kpmovies.utils.DataResult

interface Repository {

    fun getMoviesPreview(type: MovieType, limit: Int): Flow<PagingData<MoviePreview>>

    suspend fun getMovie(movieId: Int): DataResult<Movie>

    fun getReviews(movieId: Int): Flow<PagingData<Review>>
}