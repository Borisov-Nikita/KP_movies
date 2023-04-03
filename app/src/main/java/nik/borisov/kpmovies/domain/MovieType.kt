package nik.borisov.kpmovies.domain

enum class MovieType(val type: String) {

    TYPE_MOVIE("movie"),
    TYPE_TV_SERIES("tv-series"),
    TYPE_CARTOON("cartoon"),
    TYPE_ANIME("anime"),
    TYPE_ANIMATED_SERIES("animated-series"),
    TYPE_UNDEFINED("undefined")
}