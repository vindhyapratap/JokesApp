package com.unlimint.jokesapp.repositories

import com.unlimint.jokesapp.models.Joke

class JokeRepositoryImpl(private val jokeService: JokeService) : JokeRepository {
    override suspend fun getRandomJoke(): Joke {
        return jokeService.getRandomJoke()
    }
}
