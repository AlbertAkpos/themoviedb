package com.alberto.themoviedb.data.interfaces

import com.alberto.themoviedb.data.models.Remote

interface IRemoteSource {
    suspend fun fetchMovies(page: Int): Remote.PopularMoviesResponse
    suspend fun fetchMoviePictures(movieId: String): Remote.MoviePicturesResponse
}