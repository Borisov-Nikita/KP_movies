package nik.borisov.kpmovies.domain.entities

import nik.borisov.kpmovies.domain.MovieType

data class MoviePreview(
    val id: Int,
    val name: String,
    val type: MovieType,
    val year: Int,
    val rating: Double,
    val movieLength: Int,
    val poster: String,
    val genres: List<String>,
    val countries: List<String>
)
