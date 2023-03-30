package nik.borisov.kpmovies.data.network.models

import com.google.gson.annotations.SerializedName

data class TrailerDto(

    @SerializedName("url")
    val url: String? = null,
    @SerializedName("name")
    val name:String? = null
)
