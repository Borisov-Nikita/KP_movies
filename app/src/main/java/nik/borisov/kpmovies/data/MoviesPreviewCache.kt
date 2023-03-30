package nik.borisov.kpmovies.data

import nik.borisov.kpmovies.domain.entities.MoviePreview

object MoviesPreviewCache {

    val cache = mutableMapOf<MovieType, List<MoviePreview>>(
    )
}