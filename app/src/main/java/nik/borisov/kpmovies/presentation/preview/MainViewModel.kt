package nik.borisov.kpmovies.presentation.preview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nik.borisov.kpmovies.data.MovieType
import nik.borisov.kpmovies.data.RepositoryImpl
import nik.borisov.kpmovies.domain.entities.MoviePreview
import nik.borisov.kpmovies.domain.usecases.GetMoviesPreviewUseCase

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = RepositoryImpl()

    private val getMoviesPreviewUseCase = GetMoviesPreviewUseCase(repository)

    private val _movies = MutableLiveData<List<MoviePreview>>()
    val movies: LiveData<List<MoviePreview>>
        get() = _movies

    private val _tvSeries = MutableLiveData<List<MoviePreview>>()
    val tvSeries: LiveData<List<MoviePreview>>
        get() = _tvSeries

    private val _cartoons = MutableLiveData<List<MoviePreview>>()
    val cartoons: LiveData<List<MoviePreview>>
        get() = _cartoons

    private val _anime = MutableLiveData<List<MoviePreview>>()
    val anime: LiveData<List<MoviePreview>>
        get() = _anime

    private val _animatedSeries = MutableLiveData<List<MoviePreview>>()
    val animatedSeries: LiveData<List<MoviePreview>>
        get() = _animatedSeries

    init {
        getMoviesPreview(MovieType.TYPE_MOVIE)
        getMoviesPreview(MovieType.TYPE_TV_SERIES)
        getMoviesPreview(MovieType.TYPE_CARTOON)
        getMoviesPreview(MovieType.TYPE_ANIME)
        getMoviesPreview(MovieType.TYPE_ANIMATED_SERIES)
    }

    fun getMoviesPreview(type: MovieType) {
        viewModelScope.launch {
            val result = getMoviesPreviewUseCase.getMoviesPreview(type, DOWNLOAD_LIMIT)
            result.map {
                //To custom model for adapter
                //prepare data to show
            }

            when(type){
                MovieType.TYPE_MOVIE -> _movies.value = result
                MovieType.TYPE_TV_SERIES -> _tvSeries.value = result
                MovieType.TYPE_CARTOON -> _cartoons.value = result
                MovieType.TYPE_ANIME -> _anime.value = result
                MovieType.TYPE_ANIMATED_SERIES -> _animatedSeries.value = result
            }
        }
    }

    companion object {

        private const val DOWNLOAD_LIMIT = 10
    }
}