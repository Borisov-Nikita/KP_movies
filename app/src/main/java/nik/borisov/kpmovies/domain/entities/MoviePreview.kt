package nik.borisov.kpmovies.domain.entities

data class MoviePreview(
    val id: Int,
    val name: String,
    val type: String,
    val year: Int,
    val rating: Double,
    val movieLength: Int,
    val poster: String,
    val genres: List<String>,
    val countries: List<String>
)
