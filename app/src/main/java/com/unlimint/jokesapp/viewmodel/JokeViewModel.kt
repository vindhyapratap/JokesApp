package com.unlimint.jokesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unlimint.jokesapp.models.Joke
import com.unlimint.jokesapp.repositories.JokeRepository
import com.unlimint.localdata.JokeStorage
import kotlinx.coroutines.*

class JokeViewModel(private val jokeRepository: JokeRepository) : ViewModel() {
    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> get() = _jokes
    private var timerJob: Job? = null

    init {
        val savedJokes = JokeStorage.getSavedJokes() // Load saved jokes from storage
        _jokes.value = savedJokes
        loadJokes()
    }

    private fun loadJokes() {
        viewModelScope.launch {
            val newJoke = jokeRepository.getRandomJoke()
            val currentJokes = _jokes.value.orEmpty()

            val updatedJokes = if (currentJokes.size >= 10) {
                val newJokes = currentJokes.toMutableList().apply {
                    removeAt(0)
                    add(newJoke)
                }
                newJokes.toList()
            } else {
                currentJokes + newJoke
            }

            _jokes.value = updatedJokes
            JokeStorage.saveJokes(updatedJokes)
        }
    }


    fun startFetchingJokes() {
        timerJob?.cancel()
        timerJob = CoroutineScope(Dispatchers.IO).launch {
            while (timerJob?.isActive == true) {
                delay(60000) // Fetch jokes every 1 minute
                loadJokes()
            }
        }

    }



    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
