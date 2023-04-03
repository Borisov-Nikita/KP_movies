package nik.borisov.kpmovies.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import nik.borisov.kpmovies.R
import nik.borisov.kpmovies.databinding.ActivityMovieDetailBinding

class MovieDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMovieDetailBinding.inflate(LayoutInflater.from(this))
    }

    private val movieId by lazy {
        parseIntent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            launchFragment()
        }
    }

    private fun parseIntent(): Int {
        if (!intent.hasExtra(EXTRA_MOVIE_ID)) {
            throw IllegalArgumentException("Param movie id is absent.")
        }
        val id = intent.getIntExtra(EXTRA_MOVIE_ID, UNDEFINED_ID)
        if (id == UNDEFINED_ID) {
            throw IllegalArgumentException("Param movie id is undefined.")
        }
        return id
    }

    private fun launchFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.movieDetailContainer, MovieDetailFragment.newInstance(movieId))
            .commit()
    }

    companion object {

        private const val EXTRA_MOVIE_ID = "extra_movie_id"
        private const val UNDEFINED_ID = -1

        fun newIntent(context: Context, movieId: Int): Intent {
            return Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_ID, movieId)
            }
        }
    }
}