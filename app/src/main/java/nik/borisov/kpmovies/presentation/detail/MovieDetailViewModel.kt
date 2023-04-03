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
import nik.borisov.kpmovies.utils.DataResult

class MovieDetailViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = RepositoryImpl()

    private val getMovieUseCase = GetMovieUseCase(repository)

    private val _movie = MutableLiveData<DataResult<Movie>>()
    val movie: LiveData<DataResult<Movie>>
        get() = _movie

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            val result = getMovieUseCase.getMovie(movieId)
            if (result.data != null) {
                _movie.value = result
            }
        }
    }
}