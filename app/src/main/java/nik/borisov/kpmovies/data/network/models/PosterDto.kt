package nik.borisov.kpmovies.data.network.models

import com.google.gson.annotations.SerializedName

data class PosterDto(

    @SerializedName("url")
    val url: String? = null,
    @SerializedName("previewUrl")
    val previewUrl: String ? = null
)
