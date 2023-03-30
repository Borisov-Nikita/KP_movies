package nik.borisov.kpmovies.data.network.models

import com.google.gson.annotations.SerializedName

data class TrailersResponse(

    @SerializedName("trailers")
    val trailers: List<TrailerDto>
)
