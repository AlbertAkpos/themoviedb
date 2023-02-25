package com.alberto.themoviedb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.alberto.themoviedb.R
import com.alberto.themoviedb.data.models.Domain
import com.alberto.themoviedb.databinding.FragmentMovieListBinding
import com.alberto.themoviedb.helper.ResultState
import com.alberto.themoviedb.helper.extensions.show
import com.alberto.themoviedb.helper.extensions.showDialog
import com.alberto.themoviedb.helper.extensions.toJson
import com.alberto.themoviedb.helper.extensions.visibleIf
import com.alberto.themoviedb.helper.getNavBuilderWithAnimations
import com.alberto.themoviedb.ui.adapter.MovieAdapter
import com.alberto.themoviedb.ui.viewmodel.MovieVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {


    private val movieAdapter by lazy { MovieAdapter(::onMovieClicked) }

    private lateinit var binding: FragmentMovieListBinding

    private val viewModel by viewModels<MovieVM>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setObservers()
    }

    var some = false
    private fun setObservers() {
        viewModel.movieState.observe(viewLifecycleOwner) {
            val state = it.getContentIfNotHandled() ?: return@observe
            when(state) {
                is ResultState.Loading -> handleLoading(true)
                is ResultState.Error -> {
                    handleLoading(false)
                    context?.showDialog(title = "Error", message = state.message, negativeTitle = "Cancel") {
                        viewModel.fetchMovies()
                    }
                }
                is ResultState.Success -> {
                    handleLoading(false)
                    val list = movieAdapter.currentList + state.data
                    movieAdapter.submitList(list)
                }
            }
        }
    }

    private fun setupUI() {
        binding.movieList.adapter = movieAdapter
        binding.movieList.addOnScrollListener(scrollListener)

    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            val loading = binding.loadingMore.visibility == View.VISIBLE
            if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE && !loading) {
                viewModel.fetchMovies()
            }
        }
    }

    private fun handleLoading(loading: Boolean) {
        val currentPage = viewModel.currentPage
        if (currentPage > 1) {
            binding.loadingMore.visibleIf(loading)
        } else {
           // binding.movieList.show()
            binding.firstLoadIndicator.visibleIf(loading)
        }
    }

    private fun onMovieClicked(movie: Domain.Movie) {
        val bundle = Bundle().apply {
            putString(MovieDetailFragment.BUNDLE_KEY, movie.toJson())
        }
        findNavController().navigate(R.id.movieDetailFragment, bundle, getNavBuilderWithAnimations().build())
    }
}