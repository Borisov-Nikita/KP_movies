package nik.borisov.kpmovies.data

import nik.borisov.kpmovies.domain.entities.MoviePreview

object MoviesPreviewCache {

    var cache = mutableMapOf<String, List<MoviePreview>>(
    )
}