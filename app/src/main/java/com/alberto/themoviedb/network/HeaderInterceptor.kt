package com.alberto.themoviedb.network

import com.alberto.themoviedb.BuildConfig
import com.alberto.themoviedb.helper.Headers
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val oldUrl = request.url
        val newUrl = oldUrl.newBuilder().addQueryParameter(Headers.API_KEY, BuildConfig.API_KEY)
        val newRequest = request.newBuilder().url(newUrl.build())
        return chain.proceed(newRequest.build())
    }
}