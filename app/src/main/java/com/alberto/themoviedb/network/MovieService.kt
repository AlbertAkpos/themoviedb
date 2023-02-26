package com.alberto.themoviedb.network

import com.alberto.themoviedb.data.models.Remote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun fetchPopularMovies(@Query("page") page: Int): Remote.PopularMoviesResponse

    @GET("movie/{movieId}/images")
    suspend fun fetchMovieImages(@Path("movieId") movieId: String): Remote.MoviePicturesResponse
}