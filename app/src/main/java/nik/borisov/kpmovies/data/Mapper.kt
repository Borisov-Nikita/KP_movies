package nik.borisov.kpmovies.data

import nik.borisov.kpmovies.data.network.entities.*
import nik.borisov.kpmovies.domain.ReviewType
import nik.borisov.kpmovies.domain.entities.Movie
import nik.borisov.kpmovies.domain.entities.MoviePreview
import nik.borisov.kpmovies.domain.entities.Review
import nik.borisov.kpmovies.domain.entities.Trailer

class Mapper {

    fun mapMovieDtoToEntity(movieDto: MovieDto): Movie {
        return Movie(
            id = movieDto.id,
            name = movieDto.name,
            type = movieDto.type,
            year = movieDto.year,
            description = movieDto.description,
            rating = movieDto.rating.kp,
            movieLength = movieDto.movieLength,
            poster = movieDto.poster.url,
            genres = mapGenres(movieDto.genres),
            countries = mapCountries(movieDto.countries),
            trailers = mapTrailers(movieDto.trailersResponse)
        )
    }

    fun mapMoviePreviewDtoToEntity(moviePreviewDto: MoviePreviewDto): MoviePreview {
        return MoviePreview(
            id = moviePreviewDto.id,
            name = moviePreviewDto.name,
            type = moviePreviewDto.type,
            year = moviePreviewDto.year,
            rating = moviePreviewDto.rating.kp,
            movieLength = moviePreviewDto.movieLength,
            poster = moviePreviewDto.poster.previewUrl,
            genres = mapGenres(moviePreviewDto.genres),
            countries = mapCountries(moviePreviewDto.countries)
        )
    }

    fun mapReviewDtoToEntity(reviewDto: ReviewDto): Review {
        return Review(
            id = reviewDto.id,
            movieId = reviewDto.movieId,
            title = reviewDto.title ?: "",
            type = mapReviewType(reviewDto.type),
            review = reviewDto.review,
            date = reviewDto.date,
            author = reviewDto.author
        )
    }

    private fun mapGenres(genres: List<GenreDto>) = genres.map {
        it.name
    }

    private fun mapCountries(countries: List<CountryDto>) = countries.map {
        it.name
    }

    private fun mapTrailers(trailersResponse: TrailersResponse) = mutableListOf<Trailer>().apply {
        for (trailerDto in trailersResponse.trailers) {
            add(
                Trailer(
                    url = trailerDto.url,
                    name = trailerDto.name
                )
            )
        }
    }

    private fun mapReviewType(type: String): ReviewType {
        return when (type) {
            "Позитивный" -> {
                ReviewType.TYPE_POSITIVE
            }
            "Негативный" -> {
                ReviewType.TYPE_NEGATIVE
            }
            else -> {
                ReviewType.TYPE_NEUTRAL
            }
        }
    }
}