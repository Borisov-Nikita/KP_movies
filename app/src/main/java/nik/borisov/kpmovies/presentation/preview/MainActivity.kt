package nik.borisov.kpmovies.presentation.preview

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nik.borisov.kpmovies.data.MovieType
import nik.borisov.kpmovies.databinding.ActivityMainBinding
import nik.borisov.kpmovies.domain.entities.MoviePreview
import nik.borisov.kpmovies.presentation.detail.MovieDetailActivity
import nik.borisov.kpmovies.presentation.preview.adapters.MoviesPreviewAdapter
import nik.borisov.kpmovies.utils.DataResult

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
        observeViewModel()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        binding.rvMoviesPreview.setupRecyclerViewByType(
            MovieType.TYPE_MOVIE,
            adapterMovies
        )
        binding.rvTvSeriesPreview.setupRecyclerViewByType(
            MovieType.TYPE_TV_SERIES,
            adapterTvSeries
        )
        binding.rvCartoonsPreview.setupRecyclerViewByType(
            MovieType.TYPE_CARTOON,
            adapterCartoons
        )
        binding.rvAnimePreview.setupRecyclerViewByType(
            MovieType.TYPE_ANIME,
            adapterAnime
        )
        binding.rvAnimatedSeriesPreview.setupRecyclerViewByType(
            MovieType.TYPE_ANIMATED_SERIES,
            adapterAnimatedSeries
        )
    }

    private fun RecyclerView.setupRecyclerViewByType(
        type: MovieType,
        adapter: MoviesPreviewAdapter
    ) {
        this.adapter = adapter
        this.layoutManager = createLayout()
        adapter.onMoviePreviewClickListener = onMoviePreviewClick()
        adapter.onReachEndListener = {
            viewModel.getMoviesPreview(type)
        }
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

    private fun observeViewModel() {
        viewModel.movies.observe(this) {
            setupViewByDataResult(it, adapterMovies)
        }
        viewModel.tvSeries.observe(this) {
            setupViewByDataResult(it, adapterTvSeries)
        }
        viewModel.cartoons.observe(this) {
            setupViewByDataResult(it, adapterCartoons)
        }
        viewModel.anime.observe(this) {
            setupViewByDataResult(it, adapterAnime)
        }
        viewModel.animatedSeries.observe(this) {
            setupViewByDataResult(it, adapterAnimatedSeries)
        }
    }

    private fun setupViewByDataResult(
        result: DataResult<List<MoviePreview>>,
        adapter: MoviesPreviewAdapter
    ) {
        when (result) {
            is DataResult.Success -> {
                adapter.submitList(result.data)
            }
            is DataResult.Error -> {

            }
            is DataResult.Loading -> {

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