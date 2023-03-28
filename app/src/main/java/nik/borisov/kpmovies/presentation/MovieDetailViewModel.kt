package nik.borisov.kpmovies.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nik.borisov.kpmovies.data.RepositoryImpl
import nik.borisov.kpmovies.domain.entities.Movie
import nik.borisov.kpmovies.domain.entities.Review
import nik.borisov.kpmovies.domain.usecases.GetMovieUseCase
import nik.borisov.kpmovies.domain.usecases.GetReviewsUseCase

class MovieDetailViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = RepositoryImpl()

    private val getMovieUseCase = GetMovieUseCase(repository)
    private val getReviewsUseCase = GetReviewsUseCase(repository)

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    private val _reviews = MutableLiveData<List<Review>>()
    val reviews: LiveData<List<Review>>
        get() = _reviews

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            _movie.value = getMovieUseCase.getMovie(movieId)
        }
    }

    fun getReviews(movieId: Int) {
        viewModelScope.launch {
            _reviews.value = getReviewsUseCase.getReviews(movieId)
        }
    }
}