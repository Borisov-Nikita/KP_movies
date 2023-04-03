package nik.borisov.kpmovies.data.network

import com.google.gson.GsonBuilder
import nik.borisov.kpmovies.domain.MovieType
import nik.borisov.kpmovies.data.network.jsondeserializers.MovieTypeDeserializer
import nik.borisov.kpmovies.data.network.jsondeserializers.ReviewTypeDeserializer
import nik.borisov.kpmovies.domain.ReviewType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val BASE_URL = "https://api.kinopoisk.dev/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(createGson()))
        .client(createOkHttpClient())
        .build()

    private fun createHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private fun createOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(createHttpLoggingInterceptor())
        .build()

    private fun createGson() = GsonBuilder()
        .registerTypeAdapter(ReviewType::class.java, ReviewTypeDeserializer())
        .registerTypeAdapter(MovieType::class.java, MovieTypeDeserializer())
        .serializeNulls()
        .create()


    val apiService = retrofit.create(ApiService::class.java)
}