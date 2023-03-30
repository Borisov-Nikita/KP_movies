package nik.borisov.kpmovies.presentation.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nik.borisov.kpmovies.data.RepositoryImpl
import nik.borisov.kpmovies.domain.entities.Movie
import nik.borisov.kpmovies.domain.usecases.GetMovieUseCase

class MovieDetailViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = RepositoryImpl()

    private val getMovieUseCase = GetMovieUseCase(repository)

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            _movie.value = getMovieUseCase.getMovie(movieId)
        }
    }
}