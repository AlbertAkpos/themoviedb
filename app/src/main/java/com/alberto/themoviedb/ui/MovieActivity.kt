package com.alberto.themoviedb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alberto.themoviedb.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
    }
}