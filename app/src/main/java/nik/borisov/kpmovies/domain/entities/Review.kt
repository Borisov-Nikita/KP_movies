package nik.borisov.kpmovies.domain.entities

import nik.borisov.kpmovies.domain.ReviewType
import java.io.Serializable

data class Review(

    val id: Int,
    val movieId: Int,
    val type: ReviewType,
    val review: String,
    val date: String,
    val author: String
) : Serializable