package nik.borisov.kpmovies.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import nik.borisov.kpmovies.data.network.models.ReviewDto

class ReviewPagingSource(
    private val apiService: ApiService,
    private val movieId: Int
) : PagingSource<Int, ReviewDto>() {

    override fun getRefreshKey(state: PagingState<Int, ReviewDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewDto> {
        val page = params.key ?: START_PAGE
        return try {
            val response = apiService.loadReviews(
                movieId = movieId,
                page = page
            )
            val reviews = response.body()?.reviews ?: emptyList()
            val nextKey = if (reviews.isEmpty()) null else page.plus(1)
            val prevKey = if (page == START_PAGE) null else page.minus(1)
            LoadResult.Page(
                data = reviews,
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