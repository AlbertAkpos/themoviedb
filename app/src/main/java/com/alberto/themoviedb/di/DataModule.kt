package com.alberto.themoviedb.di

import android.content.Context
import com.alberto.themoviedb.BuildConfig
import com.alberto.themoviedb.data.interfaces.ILocalSource
import com.alberto.themoviedb.data.interfaces.IRemoteSource
import com.alberto.themoviedb.data.interfaces.IRepository
import com.alberto.themoviedb.data.local.Database
import com.alberto.themoviedb.data.local.LocalSource
import com.alberto.themoviedb.data.remote.RemoteSource
import com.alberto.themoviedb.data.repository.Repository
import com.alberto.themoviedb.helper.NamedParams
import com.alberto.themoviedb.network.HeaderInterceptor
import com.alberto.themoviedb.network.MovieService
import com.alberto.themoviedb.network.RemoteClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
internal abstract  class DataModule {

    @Binds
    abstract fun provideLocalSource(localSource: LocalSource): ILocalSource

    @Binds
    abstract fun provideRemoteSource(remoteSource: RemoteSource): IRemoteSource

    @Binds
    abstract fun provideRepository(repository: Repository): IRepository


    companion object {
        @Provides
        fun provideRoomDb(@ApplicationContext  context: Context): Database {
            return Database.getDatabase(context)
        }

        @Provides
        fun provideMovieDao(database: Database) = database.movieDao

        @Provides
        fun provideIOContext() = Dispatchers.IO
    }
}