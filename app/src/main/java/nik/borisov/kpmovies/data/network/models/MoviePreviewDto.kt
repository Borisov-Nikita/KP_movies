package nik.borisov.kpmovies.data.network.models

import com.google.gson.annotations.SerializedName

data class MoviePreviewDto(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("type")
    val type: String,
    @SerializedName("year")
    val year: Int ?= null,
    @SerializedName("rating")
    val rating: RatingDto,
    @SerializedName("movieLength")
    val movieLength: Int ?= null,
    @SerializedName("poster")
    val poster: PosterDto,
    @SerializedName("genres")
    val genres: List<GenreDto>,
    @SerializedName("countries")
    val countries: List<CountryDto>
)
