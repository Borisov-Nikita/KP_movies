package nik.borisov.kpmovies.domain.repositories

import nik.borisov.kpmovies.data.MovieType
import nik.borisov.kpmovies.domain.entities.Movie
import nik.borisov.kpmovies.domain.entities.MoviePreview
import nik.borisov.kpmovies.domain.entities.Review
import nik.borisov.kpmovies.utils.DataResult

interface Repository {

    suspend fun getMoviesPreview(type: MovieType, limit: Int):DataResult<List<MoviePreview>>

    suspend fun getMovie(movieId: Int): DataResult<Movie>

    suspend fun getReviews(movieId: Int, page: Int): DataResult<List<Review>>
}