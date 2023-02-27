package com.alberto.themoviedb.data.repository

import com.alberto.themoviedb.data.interfaces.ILocalSource
import com.alberto.themoviedb.data.interfaces.IRemoteSource
import com.alberto.themoviedb.data.interfaces.IRepository
import com.alberto.themoviedb.data.fakes.Api
import com.alberto.themoviedb.data.fakes.Database
import com.alberto.themoviedb.data.fakes.FakeLocalSource
import com.alberto.themoviedb.data.fakes.FakeRemoteSource
import com.alberto.themoviedb.data.models.Local
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RepositoryTest {
    private val textCoroutineDispatcher = TestCoroutineDispatcher()
    private val fakeLocalSource: ILocalSource = FakeLocalSource(database = Database())
    private val fakeRemoteSource: IRemoteSource = FakeRemoteSource(api = Api())
    private val repository  = Repository(localSource = fakeLocalSource, remoteSource = fakeRemoteSource, dispatcher = textCoroutineDispatcher)

    @Before
    fun setup() {
        Dispatchers.setMain(textCoroutineDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        textCoroutineDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `assert successful fetch of REMOTE movies`() = runBlocking {
        val response = repository.fetchMovies(1)
        assertTrue(response.isNotEmpty())
    }

    @Test
    fun `assert local database is updated from remote`() = runBlocking {
        val response = repository.fetchMovies(1)
        val localDatabase = fakeLocalSource.fetchMovies(1)
        val remoteFirst = response.first()
        val localFirst = localDatabase.first()
        assert(remoteFirst.id == localFirst.id)
    }

    @Test
    fun `assert that more results is fetch when requested`() = runBlocking {
        repository.fetchMovies(1)
        //fetch page 2
        val pageTwoResponse = repository.fetchMovies(2)
        assert(pageTwoResponse.isNotEmpty())
    }

    @Test
    fun `local cache is properly updated from remote`()  = runBlocking{
        val response = repository.fetchMovies(1)
        val localCache = fakeLocalSource.fetchMovies(1)
        assert(response.size == localCache.size)
    }

    @Test
    fun `An exception is thrown to simulate invalid page number from remote`() {
        assertThrows(IllegalStateException::class.java) {
            runBlocking {
                repository.fetchMovies(3)
            }
        }
    }

    @Test
    fun `local cache returns correct number of inserted items`() = runBlocking {
        val movies = arrayListOf(
            Local.Movie(1L, "test1", "overview1", "/image", 1, 3.5F, "/backdrop"),
            Local.Movie(2L, "test2", "overview1", "/image", 1, 3.5F, "/backdrop")
        )
        repository.insertMovies(movies)
        val fetchedMovies = repository.fetchMovies(1)
        assert(movies.size == fetchedMovies.size)
    }

    @Test
    fun `successful fetch of movie pictures`() = runBlocking {
        val movieId = "505642"
        val pictures = repository.fetchMoviePictures(movieId)
        assert(pictures.isNotEmpty())
    }


}