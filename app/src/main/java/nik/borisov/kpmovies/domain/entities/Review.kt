package nik.borisov.kpmovies.domain.entities

import nik.borisov.kpmovies.domain.ReviewType

data class Review(

    val id: Int,
    val movieId: Int,
    val title: String,
    val type: ReviewType,
    val review: String,
    val date: String,
    val author: String
)