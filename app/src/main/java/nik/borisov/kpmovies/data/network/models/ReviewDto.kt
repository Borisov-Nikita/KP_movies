package nik.borisov.kpmovies.data.network.models

import com.google.gson.annotations.SerializedName
import nik.borisov.kpmovies.domain.ReviewType

data class ReviewDto(

    @SerializedName("id")
    val id: Int,
    @SerializedName("movieId")
    val movieId: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: ReviewType?,
    @SerializedName("review")
    val review: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("author")
    val author: String
)