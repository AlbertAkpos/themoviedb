package com.alberto.themoviedb.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alberto.themoviedb.data.models.Local

@androidx.room.Database(entities = [Local.Movie::class], version = 1)
internal abstract class Database: RoomDatabase() {
    abstract val movieDao: MovieDao

    companion object {
        private const val DATABASE_NAME = "TheMovieDbDatabase"
        private var instance: Database? = null
        fun getDatabase(context: Context): Database {
            synchronized(Database::class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, Database::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return instance!!
        }
    }
}