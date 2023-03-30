package nik.borisov.kpmovies.presentation.preview

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import nik.borisov.kpmovies.data.MovieType
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
        observeViewModel()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        binding.rvMoviesPreview.setupRecyclerByType(MovieType.TYPE_MOVIE, adapterMovies)
        binding.rvSerialsPreview.setupRecyclerByType(MovieType.TYPE_TV_SERIES, adapterTvSeries)
        binding.rvCartoonsPreview.setupRecyclerByType(MovieType.TYPE_CARTOON, adapterCartoons)
        binding.rvAnimePreview.setupRecyclerByType(MovieType.TYPE_ANIME, adapterAnime)
        binding.rvAnimatedSeriesPreview.setupRecyclerByType(MovieType.TYPE_ANIMATED_SERIES, adapterAnimatedSeries)
    }

    private fun createLinearLayout() = LinearLayoutManager(
        this,
        LinearLayoutManager.HORIZONTAL,
        false
    )

    private fun RecyclerView.setupRecyclerByType(type: MovieType, adapter: MoviesPreviewAdapter) {
        this.layoutManager = createLinearLayout()
        this.adapter = adapter
        adapter.onMoviePreviewClickListener = onClickPreview()
        adapter.onReachEndListener = { viewModel.getMoviesPreview(type) }
    }

    private fun onClickPreview(): (Int) -> Unit = {
        val intent = MovieDetailActivity.newIntent(this, it)
        startActivity(intent)
    }

    private fun observeViewModel() {
        viewModel.movies.observe(this) {
            adapterMovies.submitList(it)
        }
        viewModel.tvSeries.observe(this) {
            adapterTvSeries.submitList(it)
        }
        viewModel.cartoons.observe(this) {
            adapterCartoons.submitList(it)
        }
        viewModel.anime.observe(this) {
            adapterAnime.submitList(it)
        }
        viewModel.animatedSeries.observe(this) {
            adapterAnimatedSeries.submitList(it)
        }
    }

    private fun setupClickListeners() {
        binding.tvTitleMovie.setOnClickListener {
            startActivity(CategoryPreviewActivity.newIntent(this, MovieType.TYPE_MOVIE))
        }
        binding.tvTitleTvSeries.setOnClickListener {
            startActivity(CategoryPreviewActivity.newIntent(this, MovieType.TYPE_TV_SERIES))
        }
        binding.tvTitleCartoons.setOnClickListener {
            startActivity(CategoryPreviewActivity.newIntent(this, MovieType.TYPE_CARTOON))
        }
        binding.tvTitleAnime.setOnClickListener {
            startActivity(CategoryPreviewActivity.newIntent(this, MovieType.TYPE_ANIME))
        }
        binding.tvTitleAnimatedSeries.setOnClickListener {
            startActivity(CategoryPreviewActivity.newIntent(this, MovieType.TYPE_ANIMATED_SERIES))
        }
    }
}