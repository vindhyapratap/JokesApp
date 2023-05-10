package com.unlimint.jokesapp.repositories

import com.unlimint.jokesapp.models.Joke
import retrofit2.http.GET

interface JokeService {
    @GET("api?format=json")
    suspend fun getRandomJoke(): Joke
}
