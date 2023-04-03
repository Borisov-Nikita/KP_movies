package nik.borisov.kpmovies.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nik.borisov.kpmovies.data.network.ApiFactory
import nik.borisov.kpmovies.data.network.pagingsources.MoviePreviewPagingSource
import nik.borisov.kpmovies.data.network.pagingsources.ReviewPagingSource
import nik.borisov.kpmovies.domain.MovieType
import nik.borisov.kpmovies.domain.entities.Movie
import nik.borisov.kpmovies.domain.entities.MoviePreview
import nik.borisov.kpmovies.domain.entities.Review
import nik.borisov.kpmovies.domain.repositories.Repository
import nik.borisov.kpmovies.utils.DataResult
import nik.borisov.kpmovies.utils.NetworkResponse

class RepositoryImpl : Repository, NetworkResponse() {

    private val apiService = ApiFactory.apiService
    private val mapper = Mapper()

    override fun getMoviesPreview(type: MovieType, limit: Int): Flow<PagingData<MoviePreview>> {
        return Pager(
            config = PagingConfig(pageSize = limit),
            pagingSourceFactory = {
                MoviePreviewPagingSource(
                    apiService = apiService,
                    movieType = type.type,
                    limit = limit
                )
            }
        ).flow.map { pagingData ->
            pagingData.map { mapper.mapMoviePreviewDtoToEntity(it) }
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

    override fun getReviews(movieId: Int): Flow<PagingData<Review>> {
        return Pager(
            config = PagingConfig(pageSize = DOWNLOAD_PAGE_LIMIT),
            pagingSourceFactory = {
                ReviewPagingSource(
                    apiService = apiService,
                    movieId = movieId
                )
            }
        ).flow.map { pagingData ->
            pagingData.map { mapper.mapReviewDtoToEntity(it) }
        }
    }

    companion object {

        private const val DOWNLOAD_PAGE_LIMIT = 10
    }
}