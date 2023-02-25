package com.alberto.themoviedb.di

import com.alberto.themoviedb.BuildConfig
import com.alberto.themoviedb.helper.NamedParams
import com.alberto.themoviedb.network.HeaderInterceptor
import com.alberto.themoviedb.network.MovieService
import com.alberto.themoviedb.network.RemoteClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
internal class RemoteModule {
    @Provides
    fun provideMovieService(@Named(NamedParams.MOVIE_BASE_URL) baseUrl: String, headerInterceptor: HeaderInterceptor): MovieService {
        return RemoteClient(baseUrl = baseUrl, headerInterceptor = headerInterceptor, MovieService::class.java).getService()
    }

    @Provides
    @Named(NamedParams.MOVIE_BASE_URL)
    fun provideMovieBaseUrl() = BuildConfig.MOVIE_BASE_URL

    @Provides
    fun provideHeaderInterceptor(): HeaderInterceptor = HeaderInterceptor()
}