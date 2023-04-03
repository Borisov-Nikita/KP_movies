package nik.borisov.kpmovies.data.network.jsondeserializers

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import nik.borisov.kpmovies.domain.ReviewType
import java.lang.reflect.Type

class ReviewTypeDeserializer : JsonDeserializer<ReviewType> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ReviewType {

        return when(json?.asString) {
            "Позитивный" -> {
                ReviewType.TYPE_POSITIVE
            }
            "Негативный" -> {
                ReviewType.TYPE_NEGATIVE
            }
            "Нейтральный" -> {
                ReviewType.TYPE_NEUTRAL
            }
            else -> {
                ReviewType.TYPE_UNDEFINED
            }
        }
    }
}