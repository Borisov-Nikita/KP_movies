package nik.borisov.kpmovies.domain.repositories

import nik.borisov.kpmovies.data.MovieType
import nik.borisov.kpmovies.domain.entities.Movie
import nik.borisov.kpmovies.domain.entities.MoviePreview
import nik.borisov.kpmovies.domain.entities.Review

interface Repository {

    suspend fun getMoviesPreview(type: MovieType, limit: Int): List<MoviePreview>

    suspend fun getMovie(movieId: Int): Movie

    suspend fun getReviews(movieId: Int, page: Int): List<Review>
}