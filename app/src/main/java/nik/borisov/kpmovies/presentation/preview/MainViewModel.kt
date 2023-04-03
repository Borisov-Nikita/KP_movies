package nik.borisov.kpmovies.presentation.preview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import nik.borisov.kpmovies.data.MovieType
import nik.borisov.kpmovies.data.RepositoryImpl
import nik.borisov.kpmovies.domain.entities.MoviePreview
import nik.borisov.kpmovies.domain.usecases.GetMoviesPreviewUseCase

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = RepositoryImpl()

    private val getMoviesPreviewUseCase = GetMoviesPreviewUseCase(repository)

    init {
        getMoviesPreview(MovieType.TYPE_MOVIE)
        getMoviesPreview(MovieType.TYPE_TV_SERIES)
        getMoviesPreview(MovieType.TYPE_CARTOON)
        getMoviesPreview(MovieType.TYPE_ANIME)
        getMoviesPreview(MovieType.TYPE_ANIMATED_SERIES)
    }

    fun getMoviesPreview(type: MovieType): Flow<PagingData<MoviePreview>> {
        return getMoviesPreviewUseCase.getMoviesPreview(type, DOWNLOAD_LIMIT)
            .cachedIn(viewModelScope)
    }

    companion object {

        private const val DOWNLOAD_LIMIT = 10
    }
}