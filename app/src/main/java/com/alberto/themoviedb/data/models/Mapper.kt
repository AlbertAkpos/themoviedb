package com.alberto.themoviedb.data.models

internal fun Remote.PopularMoviesResponse.local(page: Int): List<Local.Movie> {
    val movies = results?.map { Local.Movie(
            id = it.id ?: throw IllegalStateException("Invalid response"),
            imageUrl = it.posterPath ?: "", title = it.title.toString(), overview = it.overview ?: "", page = page, voteAverage = it.voteAverage ?: 0.0F) } ?: emptyList()
    return movies
}


internal fun Local.Movie.domain(): Domain.Movie {
    return Domain.Movie(title = title, overview = overview, imageUrl = imageUrl, voteAverage = voteAverage)
}