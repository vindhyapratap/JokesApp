package com.unlimint.jokesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unlimint.jokesapp.Constants
import com.unlimint.jokesapp.R
import com.unlimint.jokesapp.adapter.JokeAdapter
import com.unlimint.jokesapp.repositories.JokeRepositoryImpl
import com.unlimint.jokesapp.repositories.JokeService
import com.unlimint.jokesapp.viewmodel.JokeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var jokeAdapter: JokeAdapter
    private val viewModel: JokeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.jokeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        jokeAdapter = JokeAdapter(emptyList())
        recyclerView.adapter = jokeAdapter

        viewModel.jokes.observe(this) { jokes ->
            jokeAdapter = JokeAdapter(jokes)
            recyclerView.adapter = jokeAdapter
        }

        viewModel.startFetchingJokes()
    }
}