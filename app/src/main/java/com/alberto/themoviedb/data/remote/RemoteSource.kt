package com.alberto.themoviedb.data.remote

import com.alberto.themoviedb.data.interfaces.IRemoteSource
import com.alberto.themoviedb.data.models.Remote
import com.alberto.themoviedb.network.MovieService
import javax.inject.Inject

class RemoteSource @Inject constructor (private val service: MovieService): IRemoteSource {
    override suspend fun fetchMovies(page: Int): Remote.PopularMoviesResponse {
        return service.fetchPopularMovies(page)
    }

    override suspend fun fetchMoviePictures(movieId: String): Remote.MoviePicturesResponse {
        return service.fetchMovieImages(movieId)
    }
}