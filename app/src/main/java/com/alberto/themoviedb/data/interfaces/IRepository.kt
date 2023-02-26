package com.alberto.themoviedb.data.interfaces

import androidx.lifecycle.LiveData
import com.alberto.themoviedb.data.models.Domain
import com.alberto.themoviedb.data.models.Local

internal interface IRepository {
    suspend fun insertMovies(movies: List<Local.Movie>)
    suspend fun fetchMovies(page: Int): List<Domain.Movie>
    suspend fun fetchMoviePictures(movieId: String): List<Domain.Picture>
}