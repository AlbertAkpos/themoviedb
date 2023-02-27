package com.alberto.themoviedb.data.fakes

import com.alberto.themoviedb.data.interfaces.IRemoteSource
import com.alberto.themoviedb.data.models.Remote

class FakeRemoteSource(private val api: Api): IRemoteSource {
    override suspend fun fetchMovies(page: Int): Remote.PopularMoviesResponse {
       return api.fetchPopularMovies(page)
    }

    override suspend fun fetchMoviePictures(movieId: String): Remote.MoviePicturesResponse {
        return api.fetchMoviePictures(movieId)
    }
}