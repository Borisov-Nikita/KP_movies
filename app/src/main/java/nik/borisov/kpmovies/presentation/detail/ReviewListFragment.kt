package nik.borisov.kpmovies.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import nik.borisov.kpmovies.R
import nik.borisov.kpmovies.databinding.FragmentReviewListBinding
import nik.borisov.kpmovies.domain.entities.Review
import nik.borisov.kpmovies.presentation.detail.adapters.ReviewsAdapter
import nik.borisov.kpmovies.utils.DataResult

class ReviewListFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this)[ReviewListViewModel::class.java]
    }

    private var _binding: FragmentReviewListBinding? = null
    private val binding: FragmentReviewListBinding
        get() = _binding ?: throw NullPointerException("FragmentMovieDetailBinding is null")

    private var reviewsAdapter = ReviewsAdapter()

    private val moveName by lazy {
        parseArgsMovieName()
    }

    private val movieId by lazy {
        parseArgsMovieId()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        setupView()
        setupClickListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun parseArgsMovieName(): String {
        if (!requireArguments().containsKey(ARG_MOVIE_NAME)) {
            throw IllegalArgumentException("Argument movie name is absent.")
        }
        val movieName = requireArguments().getString(ARG_MOVIE_NAME, UNDEFINED_NAME)
        if (movieName == UNDEFINED_NAME) {
            throw IllegalArgumentException("Movie name is undefined.")
        }
        return movieName
    }

    private fun parseArgsMovieId(): Int {
        if (!requireArguments().containsKey(ARG_MOVIE_NAME)) {
            throw IllegalArgumentException("Argument movie id is absent.")
        }
        val movieId = requireArguments().getInt(ARG_MOVIE_ID, UNDEFINED_ID)
        if (movieId == UNDEFINED_ID) {
            throw IllegalArgumentException("Movie id is undefined.")
        }
        return movieId
    }

    private fun setupRecyclerView() {
        val reviewsRecyclerView = binding.rvReviews
        reviewsRecyclerView.adapter = reviewsAdapter
        reviewsRecyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        reviewsAdapter.onReachEndListener = {
            viewModel.getReviews(movieId)
        }
    }

    private fun observeViewModel() {
        viewModel.getReviews(movieId)
        viewModel.reviews.observe(viewLifecycleOwner) {
            setupViewByDataResult(it)
        }
    }

    private fun setupViewByDataResult(
        result: DataResult<List<Review>>
    ) {
        when (result) {
            is DataResult.Success -> {
                reviewsAdapter.submitList(result.data)
            }
            is DataResult.Error -> {

            }
            is DataResult.Loading -> {

            }
        }
    }

    private fun setupView() {
        binding.tvMovieName.text = moveName
    }

    private fun setupClickListener() {
        reviewsAdapter.onReviewClickListener = {
            val instance = ReviewFragment.newInstance(it)
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.movieDetailContainer, instance)
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {

        private const val UNDEFINED_NAME = ""
        private const val UNDEFINED_ID = -1

        private const val ARG_MOVIE_ID = "movie_id"
        private const val ARG_MOVIE_NAME = "movie_name"

        fun newInstance(movieName: String, movieId: Int) = ReviewListFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_MOVIE_NAME, movieName)
                putInt(ARG_MOVIE_ID, movieId)
            }
        }
    }
}