package com.alberto.themoviedb.data.models

internal object Domain {
    data class Movie(
        val title: String,
        val overview: String,
        val imageUrl: String,
        val voteAverage: Float
    )
}