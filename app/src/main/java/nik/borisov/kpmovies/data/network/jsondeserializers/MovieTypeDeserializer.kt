package nik.borisov.kpmovies.data.network.jsondeserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import nik.borisov.kpmovies.domain.MovieType
import java.lang.reflect.Type

class MovieTypeDeserializer : JsonDeserializer<MovieType> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): MovieType {

        return when (json?.asString) {
            "movie" -> {
                MovieType.TYPE_MOVIE
            }
            "tv-series" -> {
                MovieType.TYPE_TV_SERIES
            }
            "cartoon" -> {
                MovieType.TYPE_CARTOON
            }
            "anime" -> {
                MovieType.TYPE_ANIME
            }
            "animated-series" -> {
                MovieType.TYPE_ANIMATED_SERIES
            }
            else -> {
                MovieType.TYPE_UNDEFINED
            }
        }
    }
}