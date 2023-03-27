package nik.borisov.kpmovies.data.network.entities

import com.google.gson.annotations.SerializedName

data class MovieApiModel(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("year")
    val year: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("rating")
    val rating: Rating,
    @SerializedName("movieLength")
    val movieLength: Int,
    @SerializedName("poster")
    val poster: Poster,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("countries")
    val countries: List<Country>,
    @SerializedName("videos")
    val trailerList: TrailerList
)
