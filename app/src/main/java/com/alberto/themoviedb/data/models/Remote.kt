package com.alberto.themoviedb.data.models

import com.google.gson.annotations.SerializedName

object Remote {
    data class PopularMoviesResponse(
        @SerializedName("page") val page: Int?,
        @SerializedName("results") val results: List<Movie>?
    )

    data class Movie(
        @SerializedName("adult") val adult: Boolean?,
        @SerializedName("backdrop_path") val backDropPath: String?,
        @SerializedName("genre_ids") val genreIds: List<Int>?,
        @SerializedName("id") val id: Long?,
        @SerializedName("original_language") val originalLanguage: String?,
        @SerializedName("original_title") val originalTitle: String?,
        @SerializedName("overview") val overview: String?,
        @SerializedName("popularity") val popularity: Float?,
        @SerializedName("poster_path") val posterPath: String?,
        @SerializedName("release_date") val releaseDate: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("video") val video: Boolean?,
        @SerializedName("vote_average") val voteAverage: Float?,
        @SerializedName("vote_count") val voteCount: Long?
    )

    data class MoviePicturesResponse(
        @SerializedName("backdrops") val backdrops: List<Picture>?
    )

    data class Picture(
        @SerializedName("file_path") val filePath: String?
    )
}