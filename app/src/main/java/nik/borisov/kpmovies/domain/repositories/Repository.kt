package nik.borisov.kpmovies.domain.repositories

import nik.borisov.kpmovies.data.MovieType
import nik.borisov.kpmovies.domain.entities.Movie
import nik.borisov.kpmovies.domain.entities.MoviePreview

interface Repository {

    suspend fun getMoviesPreview(type: MovieType, limit: Int): List<MoviePreview>

    suspend fun getMovie(movieId: Int): Movie
}