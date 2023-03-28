package nik.borisov.kpmovies.domain.entities

data class Movie(
    val id: Int,
    val name: String,
    val type: String,
    val year: Int,
    val description: String,
    val rating: Double,
    val movieLength: Int,
    val poster: String,
    val genres: List<String>,
    val countries: List<String>,
    val trailers: List<Trailer>,
    val reviews: List<Review>
)
