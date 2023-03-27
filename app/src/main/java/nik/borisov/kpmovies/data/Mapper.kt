package nik.borisov.kpmovies.data

import nik.borisov.kpmovies.data.network.entities.*
import nik.borisov.kpmovies.domain.entities.Movie
import nik.borisov.kpmovies.domain.entities.MoviePreview

class Mapper {

    fun mapMovieApiModelToEntity(movieApiModel: MovieApiModel): Movie {
        return Movie(
            id = movieApiModel.id,
            name = movieApiModel.name,
            type = movieApiModel.type,
            year = movieApiModel.year,
            description = movieApiModel.description,
            rating = movieApiModel.rating.kp,
            movieLength = movieApiModel.movieLength,
            poster = movieApiModel.poster.url,
            genres = mapGenres(movieApiModel.genres),
            countries = mapCountries(movieApiModel.countries),
            trailerList = mapTrailerList(movieApiModel.trailerList)
        )
    }

    fun mapMoviePreviewApiModelToEntity(moviePreviewApiModel: MoviePreviewApiModel): MoviePreview {
        return MoviePreview(
            id = moviePreviewApiModel.id,
            name = moviePreviewApiModel.name,
            type = moviePreviewApiModel.type,
            year = moviePreviewApiModel.year,
            rating = moviePreviewApiModel.rating.kp,
            movieLength = moviePreviewApiModel.movieLength,
            poster = moviePreviewApiModel.poster.previewUrl,
            genres = mapGenres(moviePreviewApiModel.genres),
            countries = mapCountries(moviePreviewApiModel.countries)
        )
    }

    private fun mapGenres(genres: List<Genre>) = genres.map {
        it.name
    }

    private fun mapCountries(countries: List<Country>) = countries.map {
        it.name
    }

    private fun mapTrailerList(trailerList: TrailerList) = mutableMapOf<String, String>()
        .apply {
            for (trailer in trailerList.trailers) {
                put(trailer.name, trailer.url)
            }
        }.toMap()
}