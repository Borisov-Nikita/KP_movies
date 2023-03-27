package nik.borisov.kpmovies.data.network

import nik.borisov.kpmovies.data.network.entities.MovieApiModel
import nik.borisov.kpmovies.data.network.entities.MoviePreviewResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("v1/movie?token=WZ9AXQF-2FHMMB0-QW6CNAX-JAHXQ6Q")
    suspend fun loadMoviesPreview(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("type") type: String
    ) : MoviePreviewResponse

    @GET("v1/movie/{movieId}?token=WZ9AXQF-2FHMMB0-QW6CNAX-JAHXQ6Q")
    suspend fun loadMovie(@Path("movieId") movieId: Int) : MovieApiModel
}