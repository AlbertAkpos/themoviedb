package com.alberto.themoviedb.helper

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

object ErrorHandler {
    fun handleException(exception: Throwable): String {
        val message =  when (exception) {
            is TimeoutException -> "Connection timeout. Please try again"
            is ConnectException -> "Couldn't connect. Please check your internet"
            is SocketTimeoutException -> "Connection timeout. Please check your internet connection"
            is UnknownHostException -> "Couldn't connect to server. Please check your internet connection"
            is HttpException -> "${exception.code()}: ${exception.message}"
            else -> "Couldn't connect: ${exception.message}"
        }

        return message
    }
}