package com.alberto.themoviedb.data.fakes

import com.alberto.themoviedb.data.interfaces.ILocalSource
import com.alberto.themoviedb.data.models.Local

internal class FakeLocalSource(private val database: Database): ILocalSource {
    override suspend fun insertMovies(movies: List<Local.Movie>) {
        database.insertMovies(movies)
    }

    override suspend fun fetchMovies(page: Int): List<Local.Movie> {
       return database.fetchMovies(page)
    }

    fun clear() {
        database.clear()
    }
}