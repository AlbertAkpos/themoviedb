package com.alberto.themoviedb.data.repository

import com.alberto.themoviedb.data.interfaces.ILocalSource
import com.alberto.themoviedb.data.interfaces.IRemoteSource
import com.alberto.themoviedb.data.interfaces.IRepository
import com.alberto.themoviedb.data.models.Domain
import com.alberto.themoviedb.data.models.Local
import com.alberto.themoviedb.data.models.domain
import com.alberto.themoviedb.data.models.local
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class Repository @Inject constructor (private val localSource: ILocalSource, private val remoteSource: IRemoteSource, private val dispatcher: CoroutineDispatcher) : IRepository {
    override suspend fun insertMovies(movies: List<Local.Movie>) {
        localSource.insertMovies(movies)
    }

    override suspend fun fetchMovies(page: Int): List<Domain.Movie> = withContext(dispatcher) {
        val localMovies = localSource.fetchMovies(page)
       if (localMovies.isEmpty()) {
            val remoteMovies = remoteSource.fetchMovies(page).local(page)
            localSource.insertMovies(remoteMovies)
            localSource.fetchMovies(page).map { it.domain() }
        } else {
           localMovies.map { it.domain() }
        }

    }
}