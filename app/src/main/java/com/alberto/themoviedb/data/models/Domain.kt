package com.alberto.themoviedb.data.models

internal object Domain {
    data class Movie(
        val id: Long,
        val title: String,
        val overview: String,
        val imageUrl: String,
        val voteAverage: Float,
        val backDropImage: String
    )
}