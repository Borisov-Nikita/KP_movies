package nik.borisov.kpmovies.data

enum class MovieType(val type: String) {

    TYPE_MOVIE("movie"),
    TYPE_TV_SERIES("tv-series"),
    TYPE_CARTOON("cartoon"),
    TYPE_ANIME("anime"),
    TYPE_ANIMATED_SERIES("animated-series")
}