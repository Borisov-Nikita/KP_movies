package nik.borisov.kpmovies.data.network.entities

import com.google.gson.annotations.SerializedName

data class GenreDto(

    @SerializedName("name")
    val name: String
)
