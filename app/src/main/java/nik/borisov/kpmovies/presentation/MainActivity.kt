package nik.borisov.kpmovies.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import nik.borisov.kpmovies.data.MovieType
import nik.borisov.kpmovies.databinding.ActivityMainBinding
import nik.borisov.kpmovies.presentation.adapters.MoviesPreviewAdapter

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
        val moviesRecyclerView = binding.rvMoviesPreview
        moviesRecyclerView.adapter = adapterMovies
        moviesRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        adapterMovies.onReachEndListener = {
            viewModel.getMoviesPreview(MovieType.TYPE_MOVIE)
        }
        adapterMovies.onMoviePreviewClickListener = {
            val intent = MovieDetailActivity.newIntent(this, it)
            startActivity(intent)
        }

        val tvSeriesRecyclerView = binding.rvSerialsPreview
        tvSeriesRecyclerView.adapter = adapterTvSeries
        tvSeriesRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        adapterTvSeries.onReachEndListener = {
            viewModel.getMoviesPreview(MovieType.TYPE_TV_SERIES)
        }
        adapterTvSeries.onMoviePreviewClickListener = {
            val intent = MovieDetailActivity.newIntent(this, it)
            startActivity(intent)
        }

        val cartoonsRecyclerView = binding.rvCartoonsPreview
        cartoonsRecyclerView.adapter = adapterCartoons
        cartoonsRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        adapterCartoons.onReachEndListener = {
            viewModel.getMoviesPreview(MovieType.TYPE_CARTOON)
        }
        adapterCartoons.onMoviePreviewClickListener = {
            val intent = MovieDetailActivity.newIntent(this, it)
            startActivity(intent)
        }

        val animeRecyclerView = binding.rvAnimePreview
        animeRecyclerView.adapter = adapterAnime
        animeRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        adapterAnime.onReachEndListener = {
            viewModel.getMoviesPreview(MovieType.TYPE_ANIME)
        }
        adapterAnime.onMoviePreviewClickListener = {
            val intent = MovieDetailActivity.newIntent(this, it)
            startActivity(intent)
        }

        val animatedSeriesRecyclerView = binding.rvAnimatedSeriesPreview
        animatedSeriesRecyclerView.adapter = adapterAnimatedSeries
        animatedSeriesRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        adapterAnimatedSeries.onReachEndListener = {
            viewModel.getMoviesPreview(MovieType.TYPE_ANIMATED_SERIES)
        }
        adapterAnimatedSeries.onMoviePreviewClickListener = {
            val intent = MovieDetailActivity.newIntent(this, it)
            startActivity(intent)
        }
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