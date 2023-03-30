package nik.borisov.kpmovies.data.network

import com.google.gson.*
import nik.borisov.kpmovies.domain.ReviewType
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

object ApiFactory {

    private const val BASE_URL = "https://api.kinopoisk.dev/"

    private val retrofit = Retrofit.Builder()
        .client(createClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(createGson()))
        .build()

    private fun createClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    class ReviewTypeDeserializer : JsonDeserializer<ReviewType> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): ReviewType {

            val value = json?.asString

            return when (value) {
                "Позитивный" -> {
                    ReviewType.TYPE_POSITIVE
                }
                "Негативный" -> {
                    ReviewType.TYPE_NEGATIVE
                }
                else -> {
                    ReviewType.TYPE_NEUTRAL
                }
            }
        }
    }

    private fun createGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(ReviewType::class.java, ReviewTypeDeserializer())
            .serializeNulls()
            .create()
    }

    val apiService = retrofit.create(ApiService::class.java)
}