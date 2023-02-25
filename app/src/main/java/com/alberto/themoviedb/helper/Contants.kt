package com.alberto.themoviedb.helper

import androidx.annotation.AnimRes
import androidx.navigation.NavOptions
import com.alberto.themoviedb.R

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

fun getNavBuilderWithAnimations(
    @AnimRes enterAnim: Int = R.anim.slide_left_in,
    @AnimRes exitAnim: Int = R.anim.slide_left_out,
    @AnimRes popEnter: Int = R.anim.slide_right_in,
    @AnimRes popExitAnim: Int = R.anim.slide_right_out
): NavOptions.Builder {
    val navBuilder = NavOptions.Builder()
    navBuilder.setEnterAnim(enterAnim).setExitAnim(exitAnim)
        .setPopEnterAnim(popEnter).setPopExitAnim(popExitAnim)
    return navBuilder
}