package nik.borisov.kpmovies.data.network.entities

import com.google.gson.annotations.SerializedName

data class Rating(

    @SerializedName("kp")
    val kp: Double
)
