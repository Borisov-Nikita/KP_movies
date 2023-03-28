package nik.borisov.kpmovies.data.network.entities

import com.google.gson.annotations.SerializedName

data class TrailerDto(

    @SerializedName("url")
    val url: String,
    @SerializedName("name")
    val name:String
)
