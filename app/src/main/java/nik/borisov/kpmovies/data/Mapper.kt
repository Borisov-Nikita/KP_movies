package nik.borisov.kpmovies.data

import nik.borisov.kpmovies.data.network.models.*
import nik.borisov.kpmovies.domain.ReviewType
import nik.borisov.kpmovies.domain.entities.Movie
import nik.borisov.kpmovies.domain.entities.MoviePreview
import nik.borisov.kpmovies.domain.entities.Review
import nik.borisov.kpmovies.domain.entities.Trailer

class Mapper {

    fun mapMovieDtoToEntity(movieResponse: MovieResponse, reviewsResponse: ReviewsResponse): Movie {
        return Movie(
            id = movieResponse.id,
            name = movieResponse.name,
            type = movieResponse.type,
            year = movieResponse.year,
            description = movieResponse.description,
            rating = movieResponse.rating.kp,
            movieLength = movieResponse.movieLength,
            poster = movieResponse.poster.url,
            genres = mapGenres(movieResponse.genres),
            countries = mapCountries(movieResponse.countries),
            trailers = mapTrailers(movieResponse.trailersResponse),
            reviews = reviewsResponse.reviews.map { mapReviewDtoToEntity(it) }
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