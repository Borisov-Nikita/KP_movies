package nik.borisov.kpmovies.data.network.entities

import com.google.gson.annotations.SerializedName

data class CountryDto(

    @SerializedName("name")
    val name: String
)
