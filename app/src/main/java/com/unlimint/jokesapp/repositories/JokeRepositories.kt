package com.unlimint.jokesapp.repositories

import com.unlimint.jokesapp.models.Joke

interface JokeRepository {
    suspend fun getRandomJoke(): Joke
}
