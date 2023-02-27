package com.alberto.themoviedb.data.fakes

import com.alberto.themoviedb.data.models.Local

internal class Database {
    private val storage = arrayListOf<Local.Movie>()
    fun insertMovies(movies: List<Local.Movie>) {
        movies.map { addMovie(it) }
    }

    private fun addMovie(movie: Local.Movie) {
        if (!storage.contains(movie)) {
            storage.add(movie)
        }
    }

     fun fetchMovies(page: Int): List<Local.Movie> {
        return storage.filter { it.page == page }
    }

    fun clear() = storage.clear()
}
