package nik.borisov.kpmovies.data.network.entities

import com.google.gson.annotations.SerializedName

data class ReviewsResponse(

    @SerializedName("docs")
    val reviews: List<ReviewDto>
)