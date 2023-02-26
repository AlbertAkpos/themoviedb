package com.alberto.themoviedb.ui

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alberto.themoviedb.R
import com.alberto.themoviedb.data.models.Domain
import com.alberto.themoviedb.databinding.FragmentMovieDetailBinding
import com.alberto.themoviedb.helper.ResultState
import com.alberto.themoviedb.helper.extensions.loadImage
import com.alberto.themoviedb.helper.extensions.toObject
import com.alberto.themoviedb.ui.adapter.PictureAdapter
import com.alberto.themoviedb.ui.viewmodel.MovieVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val viewModel by viewModels<MovieVM>()

    private lateinit var binding: FragmentMovieDetailBinding

    private val picturesAdapter by lazy { PictureAdapter() }

    private val bundle by lazy { arguments?.getString(BUNDLE_KEY)?.toObject<Domain.Movie>() }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setObservers()
    }


    private fun setObservers() {
        viewModel.fetchMoviePictures(bundle?.id.toString())


        viewModel.moviePicturesState.observe(viewLifecycleOwner) {
            val state = it.getContentIfNotHandled() ?: return@observe
            if (state is ResultState.Success) {
                picturesAdapter.submitList(state.data)
            }
        }
    }

    private fun setupUI() {
        binding.title.text = bundle?.title
        binding.overview.text = bundle?.overview
        binding.backDropImage.loadImage(bundle?.backDropImage)
        binding.image.loadImage(bundle?.imageUrl)
        binding.imageList.adapter = picturesAdapter
        binding.toolbar.setNavigationOnClickListener { navigateUp() }
    }

    private fun navigateUp() {
        when (findNavController().graph.startDestinationId) {
            findNavController().currentDestination?.id -> {
                activity?.setResult(Activity.RESULT_OK)
                activity?.finish()
            }
            else -> findNavController().navigateUp()
        }
    }

    companion object {
        const val BUNDLE_KEY = "BUNDLE_KEY"
    }


}