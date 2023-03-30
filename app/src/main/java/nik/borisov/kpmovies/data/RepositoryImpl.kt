package nik.borisov.kpmovies.data

import nik.borisov.kpmovies.data.network.ApiFactory
import nik.borisov.kpmovies.domain.entities.Movie
import nik.borisov.kpmovies.domain.entities.MoviePreview
import nik.borisov.kpmovies.domain.entities.Review
import nik.borisov.kpmovies.domain.repositories.Repository
import nik.borisov.kpmovies.utils.DataResult
import nik.borisov.kpmovies.utils.NetworkResponse

class RepositoryImpl : Repository, NetworkResponse() {

    private val apiService = ApiFactory.apiService
    private val mapper = Mapper()
    private val moviePreviewCache = MoviesPreviewCache

    override suspend fun getMoviesPreview(
        type: MovieType,
        limit: Int
    ): DataResult<List<MoviePreview>> {
        val moviePreviewList = mutableListOf<MoviePreview>()
        if (moviePreviewCache.cache.containsKey(type)) {
            val currentList = moviePreviewCache.cache[type]
            moviePreviewList.addAll(currentList ?: throw RuntimeException())
        }
        val page = (moviePreviewList.size / limit) + 1
        val networkResponse = safeNetworkCall {
            apiService.loadMoviesPreview(page, limit, type.type)
        }
        return if (networkResponse is DataResult.Success) {
            moviePreviewList.addAll(networkResponse.data?.movies?.map {
                mapper.mapMoviePreviewDtoToEntity(it)
            } ?: emptyList())
            moviePreviewCache.cache[type] = moviePreviewList
            DataResult.Success(data = moviePreviewList.toList())
        } else {
            DataResult.Error(message = networkResponse.message)
        }
    }

    override suspend fun getMovie(movieId: Int): DataResult<Movie> {
        val movieNetworkResponse = safeNetworkCall {
            apiService.loadMovie(movieId)
        }
        val reviewNetworkResponse = safeNetworkCall {
            apiService.loadReviews(movieId)
        }
        return if (movieNetworkResponse is DataResult.Success && movieNetworkResponse.data != null) {
            DataResult.Success(
                mapper.mapMovieDtoToEntity(
                    movieNetworkResponse.data,
                    reviewNetworkResponse.data
                )
            )
        } else {
            DataResult.Error(message = movieNetworkResponse.message)
        }
    }

    override suspend fun getReviews(movieId: Int, page: Int): DataResult<List<Review>> {
        val networkResponse = safeNetworkCall {
            apiService.loadReviews(movieId, page)
        }
        return if (networkResponse is DataResult.Success && networkResponse.data != null) {
            DataResult.Success(networkResponse.data.reviews.map {
                mapper.mapReviewDtoToEntity(it)
            })
        } else {
            DataResult.Error(message = networkResponse.message)
        }
    }
}