package nik.borisov.kpmovies.data

import nik.borisov.kpmovies.data.network.models.*
import nik.borisov.kpmovies.domain.ReviewType
import nik.borisov.kpmovies.domain.entities.Movie
import nik.borisov.kpmovies.domain.entities.MoviePreview
import nik.borisov.kpmovies.domain.entities.Review
import nik.borisov.kpmovies.domain.entities.Trailer
import java.text.SimpleDateFormat
import java.util.*

class Mapper {

    fun mapMovieDtoToEntity(
        movieResponse: MovieResponse,
        reviewsResponse: ReviewsResponse?
    ): Movie {
        return Movie(
            id = movieResponse.id,
            name = movieResponse.name ?: UNDEFINED_STRING_FIELD,
            type = movieResponse.type,
            year = movieResponse.year ?: UNDEFINED_NUM_FIELD,
            description = movieResponse.description ?: UNDEFINED_STRING_FIELD,
            rating = movieResponse.rating.kp ?: UNDEFINED_NUM_FIELD.toDouble(),
            movieLength = movieResponse.movieLength ?: UNDEFINED_NUM_FIELD,
            poster = movieResponse.poster.url ?: UNDEFINED_STRING_FIELD,
            genres = mapGenres(movieResponse.genres),
            countries = mapCountries(movieResponse.countries),
            trailers = mapTrailers(movieResponse.trailersResponse),
            reviews = reviewsResponse?.reviews?.map { mapReviewDtoToEntity(it) }
        )
    }

    fun mapMoviePreviewDtoToEntity(moviePreviewDto: MoviePreviewDto): MoviePreview {
        return MoviePreview(
            id = moviePreviewDto.id,
            name = moviePreviewDto.name ?: UNDEFINED_STRING_FIELD,
            type = moviePreviewDto.type,
            year = moviePreviewDto.year ?: UNDEFINED_NUM_FIELD,
            rating = moviePreviewDto.rating.kp ?: UNDEFINED_NUM_FIELD.toDouble(),
            movieLength = moviePreviewDto.movieLength ?: UNDEFINED_NUM_FIELD,
            poster = moviePreviewDto.poster.previewUrl ?: UNDEFINED_STRING_FIELD,
            genres = mapGenres(moviePreviewDto.genres),
            countries = mapCountries(moviePreviewDto.countries)
        )
    }

    fun mapReviewDtoToEntity(reviewDto: ReviewDto): Review {
        return Review(
            id = reviewDto.id,
            movieId = reviewDto.movieId,
            type = mapReviewType(reviewDto.type),
            review = mapReviewText(reviewDto.title, reviewDto.review),
            date = mapReviewDate(reviewDto.date),
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
                    url = trailerDto.url ?: UNDEFINED_STRING_FIELD,
                    name = trailerDto.name ?: UNDEFINED_STRING_FIELD
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

    private fun mapReviewDate(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        val outputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)
        val input = inputFormat.parse(date)
        return outputFormat.format(input)
    }

    private fun mapReviewText(title: String?, review: String): String {
        return if (title != null) {
            "%s\n\n%s".format(title, review)
        } else {
            "%s".format(review)
        }
    }

    companion object {

        private const val UNDEFINED_STRING_FIELD = ""
        private const val UNDEFINED_NUM_FIELD = 0
    }
}