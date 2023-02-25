package com.alberto.themoviedb.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alberto.themoviedb.data.models.Local

@Dao
internal interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Local.Movie>)

    @Query("SELECT * FROM MOVIE_TABLE WHERE page=:page ORDER BY page")
    suspend fun fetchMovies(page: Int): List<Local.Movie>
}