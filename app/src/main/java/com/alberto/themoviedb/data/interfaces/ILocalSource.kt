package com.alberto.themoviedb.data.interfaces

import androidx.lifecycle.LiveData
import com.alberto.themoviedb.data.models.Local

internal interface ILocalSource {
    suspend fun insertMovies(movies: List<Local.Movie>)
    suspend fun fetchMovies(page: Int): List<Local.Movie>
}