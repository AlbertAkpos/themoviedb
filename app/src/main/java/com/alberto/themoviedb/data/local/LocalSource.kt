package com.alberto.themoviedb.data.local

import com.alberto.themoviedb.data.interfaces.ILocalSource
import com.alberto.themoviedb.data.models.Local
import javax.inject.Inject

internal class LocalSource @Inject constructor (private val movieDao: MovieDao): ILocalSource {
    override suspend fun insertMovies(movies: List<Local.Movie>) {
        movieDao.insertMovies(movies)
    }

    override suspend fun fetchMovies(page: Int): List<Local.Movie> {
       return movieDao.fetchMovies(page)
    }
}