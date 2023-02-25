package com.alberto.themoviedb.network

import com.alberto.themoviedb.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class RemoteClient<T> @Inject constructor(
    private val baseUrl: String,
    private val headerInterceptor: HeaderInterceptor,
    private val service: Class<T>
) {

    companion object {
        private const val TIMEOUT: Long = 120
        private val gson = GsonBuilder().create()
    }


    private val api: T

    init {
        val loggingInterceptor = makeLoggingInterceptor(BuildConfig.DEBUG)
        val httpClient = OkHttpClient.Builder()
            .run {
                addInterceptor(headerInterceptor)
                addInterceptor(loggingInterceptor)
                connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                readTimeout(TIMEOUT, TimeUnit.SECONDS)
            }

        val client = httpClient.build()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
        api = retrofit.create(service)
    }


    private fun makeLoggingInterceptor(debug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (debug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    fun getService() = api

}