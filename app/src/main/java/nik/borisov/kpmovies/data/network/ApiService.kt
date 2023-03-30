package nik.borisov.kpmovies.data.network

import nik.borisov.kpmovies.data.network.models.MovieResponse
import nik.borisov.kpmovies.data.network.models.MoviePreviewResponse
import nik.borisov.kpmovies.data.network.models.ReviewsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("v1/movie?token=WZ9AXQF-2FHMMB0-QW6CNAX-JAHXQ6Q")
    suspend fun loadMoviesPreview(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("type") type: String
    ): MoviePreviewResponse

    @GET("v1/movie/{movieId}?token=WZ9AXQF-2FHMMB0-QW6CNAX-JAHXQ6Q")
    suspend fun loadMovie(@Path("movieId") movieId: Int): MovieResponse

    @GET("v1/review?token=WZ9AXQF-2FHMMB0-QW6CNAX-JAHXQ6Q")
    suspend fun loadReviews(@Query("movieId") movieId: Int): ReviewsResponse

    @GET("v1/review?token=WZ9AXQF-2FHMMB0-QW6CNAX-JAHXQ6Q&sortField=date&sortType=-1&limit=20")
    suspend fun loadReviews(
        @Query("movieId") movieId: Int,
        @Query("page") page: Int
    ): ReviewsResponse
}