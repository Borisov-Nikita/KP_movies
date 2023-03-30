package nik.borisov.kpmovies.data.network.models

import com.google.gson.annotations.SerializedName

data class RatingDto(

    @SerializedName("kp")
    val kp: Double? = null
)
