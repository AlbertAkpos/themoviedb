package com.alberto.themoviedb.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alberto.themoviedb.helper.TableNames

internal object Local {

    @Entity(tableName = TableNames.MOVIE_TABLE)
    data class Movie(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "id") val id: Long,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "overview") val overview: String,
        @ColumnInfo(name = "imageUrl") val imageUrl: String,
        @ColumnInfo(name = "page") val page: Int,
        @ColumnInfo(name = "voteAverage") val voteAverage: Float,
        @ColumnInfo(name = "backDropImage") val backDropImage: String
    )
}