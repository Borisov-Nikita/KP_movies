package nik.borisov.kpmovies.data.network.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import nik.borisov.kpmovies.data.network.ApiService
import nik.borisov.kpmovies.data.network.models.MoviePreviewDto

class MoviePreviewPagingSource(
    private val apiService: ApiService,
    private val movieType: String,
    private val limit: Int
) : PagingSource<Int, MoviePreviewDto>() {

    override fun getRefreshKey(state: PagingState<Int, MoviePreviewDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviePreviewDto> {
        val page = params.key ?: START_PAGE
        return try {
            val response = apiService.loadMoviesPreview(
                page,
                limit,
                movieType
            )
            val movies = response.body()?.movies ?: emptyList()
            val nextKey = if (movies.isEmpty()) null else page.plus(1)
            val prevKey = if (page == START_PAGE) null else page.minus(1)
            LoadResult.Page(
                data = movies,
                nextKey = nextKey,
                prevKey = prevKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }

    }

    companion object {

        private const val START_PAGE = 1
    }
}