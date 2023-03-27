package nik.borisov.kpmovies.data.network.entities

import com.google.gson.annotations.SerializedName

data class TrailerList(

    @SerializedName("trailers")
    val trailers: List<Trailer>
)
