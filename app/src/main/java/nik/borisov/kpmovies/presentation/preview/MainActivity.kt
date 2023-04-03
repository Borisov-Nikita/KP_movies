package nik.borisov.kpmovies.presentation.preview

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import nik.borisov.kpmovies.domain.MovieType
import nik.borisov.kpmovies.databinding.ActivityMainBinding
import nik.borisov.kpmovies.presentation.detail.MovieDetailActivity
import nik.borisov.kpmovies.presentation.preview.adapters.MoviesPreviewAdapter

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }

    private val adapterMovies = MoviesPreviewAdapter()
    private val adapterTvSeries = MoviesPreviewAdapter()
    private val adapterCartoons = MoviesPreviewAdapter()
    private val adapterAnime = MoviesPreviewAdapter()
    private val adapterAnimatedSeries = MoviesPreviewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()
        collectMoviesPreview()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        binding.rvMoviesPreview.setupRecyclerViewByType(
            adapterMovies
        )
        binding.rvTvSeriesPreview.setupRecyclerViewByType(
            adapterTvSeries
        )
        binding.rvCartoonsPreview.setupRecyclerViewByType(
            adapterCartoons
        )
        binding.rvAnimePreview.setupRecyclerViewByType(
            adapterAnime
        )
        binding.rvAnimatedSeriesPreview.setupRecyclerViewByType(
            adapterAnimatedSeries
        )
    }

    private fun RecyclerView.setupRecyclerViewByType(
        adapter: MoviesPreviewAdapter
    ) {
        this.adapter = adapter
        this.layoutManager = createLayout()
        adapter.onMoviePreviewClickListener = onMoviePreviewClick()
    }

    private fun createLayout() = LinearLayoutManager(
        this,
        LinearLayoutManager.HORIZONTAL,
        false
    )

    private fun onMoviePreviewClick(): (Int) -> Unit = {
        val intent = MovieDetailActivity.newIntent(this, it)
        startActivity(intent)
    }

    private fun collectMoviesPreview() {
        collectMoviesPreviewByType(MovieType.TYPE_MOVIE, adapterMovies)
        collectMoviesPreviewByType(MovieType.TYPE_TV_SERIES, adapterTvSeries)
        collectMoviesPreviewByType(MovieType.TYPE_CARTOON, adapterCartoons)
        collectMoviesPreviewByType(MovieType.TYPE_ANIME, adapterAnime)
        collectMoviesPreviewByType(MovieType.TYPE_ANIMATED_SERIES,adapterAnimatedSeries)
    }

    private fun collectMoviesPreviewByType(type: MovieType, adapter: MoviesPreviewAdapter){
        lifecycleScope.launch {
            viewModel.getMoviesPreview(type).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun setupClickListeners() {
        binding.tvTitleMovie.setOnClickListener {
            startCategoryPreviewActivity(MovieType.TYPE_MOVIE)
        }
        binding.tvTitleTvSeries.setOnClickListener {
            startCategoryPreviewActivity(MovieType.TYPE_TV_SERIES)
        }
        binding.tvTitleCartoons.setOnClickListener {
            startCategoryPreviewActivity(MovieType.TYPE_CARTOON)
        }
        binding.tvTitleAnime.setOnClickListener {
            startCategoryPreviewActivity(MovieType.TYPE_ANIME)
        }
        binding.tvTitleAnimatedSeries.setOnClickListener {
            startCategoryPreviewActivity(MovieType.TYPE_ANIMATED_SERIES)
        }
    }

    private fun startCategoryPreviewActivity(type: MovieType) {
        startActivity(CategoryPreviewActivity.newIntent(this, type))
    }
}