package nik.borisov.kpmovies.data.network.entities

import com.google.gson.annotations.SerializedName

data class MoviePreviewResponse(

    @SerializedName("docs")
    val movies: List<MoviePreviewApiModel>
)
