package nik.borisov.kpmovies.presentation

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import nik.borisov.kpmovies.R
import nik.borisov.kpmovies.data.MovieType
import nik.borisov.kpmovies.databinding.ActivityCategoryPreviewBinding
import nik.borisov.kpmovies.presentation.adapters.CategoryPreviewAdapter

class CategoryPreviewActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val binding by lazy {
        ActivityCategoryPreviewBinding.inflate(LayoutInflater.from(this))
    }

    private val adapter = CategoryPreviewAdapter()

    private val movieType by lazy {
        parseIntent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()
        observeViewModel()
        setupView()
        setupClickListeners()
    }

    private fun parseIntent(): MovieType {
        if (!intent.hasExtra(EXTRA_MOVIE_TYPE)) {
            throw IllegalArgumentException("Param movie type is absent.")
        }
        val type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(EXTRA_MOVIE_TYPE, MovieType::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra(EXTRA_MOVIE_TYPE) as? MovieType
        }
        return type ?: throw IllegalArgumentException("Movie type is null")
    }

    private fun setupRecyclerView() {
        val categoryPreviewRecyclerView = binding.rvCategoryPreview
        categoryPreviewRecyclerView.adapter = adapter
        categoryPreviewRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter.onReachEndListener = {
            viewModel.getMoviesPreview(movieType)
        }
    }

    private fun observeViewModel() {
        when (movieType) {
            MovieType.TYPE_MOVIE -> {
                viewModel.movies.observe(this) {
                    adapter.submitList(it)
                }
            }
            MovieType.TYPE_TV_SERIES -> {
                viewModel.tvSeries.observe(this) {
                    adapter.submitList(it)
                }
            }
            MovieType.TYPE_CARTOON -> {
                viewModel.cartoons.observe(this) {
                    adapter.submitList(it)
                }
            }
            MovieType.TYPE_ANIME -> {
                viewModel.anime.observe(this) {
                    adapter.submitList(it)
                }
            }
            MovieType.TYPE_ANIMATED_SERIES -> {
                viewModel.animatedSeries.observe(this) {
                    adapter.submitList(it)
                }
            }
        }
    }

    private fun setupView() {
        binding.tvTitle.text = when (movieType) {
            MovieType.TYPE_MOVIE -> getString(R.string.movies)
            MovieType.TYPE_TV_SERIES -> getString(R.string.tv_series)
            MovieType.TYPE_CARTOON -> getString(R.string.cartoons)
            MovieType.TYPE_ANIME -> getString(R.string.anime)
            MovieType.TYPE_ANIMATED_SERIES -> getString(R.string.animated_series)
        }
    }

    private fun setupClickListeners() {
        binding.ibCloseActivity.setOnClickListener {
            finish()
        }
    }

    companion object {

        private const val EXTRA_MOVIE_TYPE = "extra_movie_type"

        fun newIntent(context: Context, type: MovieType): Intent {
            return Intent(context, CategoryPreviewActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_TYPE, type)
            }
        }
    }
}