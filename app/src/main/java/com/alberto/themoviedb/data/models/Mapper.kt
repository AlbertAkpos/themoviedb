package com.alberto.themoviedb.data.models

import com.alberto.themoviedb.BuildConfig

internal fun Remote.PopularMoviesResponse.local(page: Int): List<Local.Movie> {
    val movies = results?.map { Local.Movie(
        id = it.id ?: throw IllegalStateException("Invalid response"),
        imageUrl = BuildConfig.IMAGE_BASE_URL + (it.posterPath ?: ""),
        title = it.title.toString(),
        overview = it.overview ?: "",
        page = page,
        voteAverage = it.voteAverage ?: 0.0F,
        backDropImage =  BuildConfig.IMAGE_BASE_URL + (it.backDropPath ?: "")
    )
    } ?: emptyList()
    return movies
}


internal fun Local.Movie.domain(): Domain.Movie {
    return Domain.Movie(
        id = id,
        title = title,
        overview = overview,
        imageUrl = imageUrl,
        voteAverage = voteAverage,
        backDropImage = backDropImage
    )
}

internal fun Remote.MoviePicturesResponse.domain(): List<Domain.Picture> {
    return backdrops?.map {
        Domain.Picture(imageUrl = BuildConfig.IMAGE_BASE_URL + it.filePath.toString())
    } ?: emptyList()
}