package nik.borisov.kpmovies.data

import nik.borisov.kpmovies.data.network.ApiFactory
import nik.borisov.kpmovies.domain.entities.Movie
import nik.borisov.kpmovies.domain.entities.MoviePreview
import nik.borisov.kpmovies.domain.entities.Review
import nik.borisov.kpmovies.domain.repositories.Repository

class RepositoryImpl : Repository {

    private val apiService = ApiFactory.apiService
    private val mapper = Mapper()
    private val cache = MoviesPreviewCache

    override suspend fun getMoviesPreview(type: MovieType, limit: Int): List<MoviePreview> {
        val moviePreviewList = mutableListOf<MoviePreview>()
        if (cache.cache.containsKey(type.type)) {
            val currentList = cache.cache[type.type]
            moviePreviewList.addAll(currentList ?: throw RuntimeException())
        }
        val page = (moviePreviewList.size / limit) + 1
        moviePreviewList.addAll(apiService.loadMoviesPreview(
            page,
            limit,
            type.type
        ).movies.map {
            mapper.mapMoviePreviewDtoToEntity(it)
        })
        cache.cache[type.type] = moviePreviewList
        return moviePreviewList.toList()
    }

    override suspend fun getMovie(movieId: Int): Movie {
        return mapper.mapMovieDtoToEntity(apiService.loadMovie(movieId))
    }

    override suspend fun getReviews(movieId: Int): List<Review> {
        return apiService.loadReviews(movieId).reviews.map {
            mapper.mapReviewDtoToEntity(it)
        }
    }
}