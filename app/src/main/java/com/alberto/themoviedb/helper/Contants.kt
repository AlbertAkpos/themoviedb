package com.alberto.themoviedb.helper

internal object Headers {
    const val API_KEY = "api_key"
}

internal object NamedParams {
    const val MOVIE_BASE_URL = "MOVIE_BASE_URL"
}

internal object TableNames {
    const val MOVIE_TABLE = "MOVIE_TABLE"
}

internal sealed class ResultState<T> {
    class Loading<T> : ResultState<T>()
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error<T>(val message: String) : ResultState<T>()
}