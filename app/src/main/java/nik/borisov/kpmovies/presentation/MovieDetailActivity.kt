package nik.borisov.kpmovies.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import nik.borisov.kpmovies.databinding.ActivityMovieDetailBinding
import nik.borisov.kpmovies.domain.entities.Movie
import nik.borisov.kpmovies.presentation.adapters.ReviewsAdapter
import nik.borisov.kpmovies.presentation.adapters.TrailersAdapter

class MovieDetailActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[MovieDetailViewModel::class.java]
    }

    private val binding by lazy {
        ActivityMovieDetailBinding.inflate(LayoutInflater.from(this))
    }

    private val trailersAdapter = TrailersAdapter()
    private val reviewsAdapter = ReviewsAdapter()

    private val movieId by lazy {
        parseIntent()
    }

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeViewModel()
        setupRecyclerView()
        setupClickListeners()
    }

    private fun parseIntent(): Int {
        if (!intent.hasExtra(EXTRA_MOVIE_ID)) {
            throw IllegalArgumentException("Param movie id is absent.")
        }
        val id = intent.getIntExtra(EXTRA_MOVIE_ID, UNDEFINED_ID)
        if (id < 0) {
            throw IllegalArgumentException("Param movie id is undefined.")
        }
        return id
    }

    private fun setupRecyclerView() {
        val trailersRecyclerView = binding.rvTrailers
        trailersRecyclerView.adapter = trailersAdapter
        trailersRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        val reviewsRecyclerView = binding.rvReviews
        reviewsRecyclerView.adapter = reviewsAdapter
        reviewsRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun observeViewModel() {
        viewModel.getMovie(movieId)
        viewModel.movie.observe(this) {
            movie = it
            setupView(it)
            trailersAdapter.submitList(it.trailers)
        }
        viewModel.getReviews(movieId)
        viewModel.reviews.observe(this) {
            reviewsAdapter.submitList(it)
        }
    }

    private fun setupView(movie: Movie) {
        Glide.with(this)
            .load(movie.poster)
            .into(binding.ivPoster)
        binding.tvMovieName.text = movie.name
        binding.tvMovieRating.text = "Rating KP: %.1f".format(movie.rating)
        binding.tvMovieYearAndGenre.text = "%d, %s".format(
            movie.year,
            movie.genres.toString().trim('[', ']')
        )
        binding.tvMovieCountryAndLength.text = "%s, %d:%02d".format(
            movie.countries.toString().trim('[', ']'),
            movie.movieLength / MIN_IN_HOUR,
            movie.movieLength % MIN_IN_HOUR
        )
        binding.tvMovieDescription.text = movie.description
    }

    private fun setupClickListeners() {
        trailersAdapter.onTrailerClickListener = {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(it)
            startActivity(intent)
        }
    }

    companion object {

        private const val EXTRA_MOVIE_ID = "extra_movie_id"
        private const val UNDEFINED_ID = -1
        private const val MIN_IN_HOUR = 60

        fun newIntent(context: Context, movieId: Int): Intent {
            return Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_ID, movieId)
            }
        }
    }
}