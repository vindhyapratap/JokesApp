package com.unlimint.localdata

import android.content.Context
import com.google.gson.Gson
import com.unlimint.jokesapp.JokesApplication
import com.unlimint.jokesapp.models.Joke

object JokeStorage {
    private const val PREFS_NAME = "JokePrefs"
    private const val JOKES_KEY = "Jokes"

    fun getSavedJokes(): List<Joke> {
        val prefs = JokesApplication.appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(JOKES_KEY, null)
        return if (json != null) {
            val jokesArray = Gson().fromJson(json, Array<Joke>::class.java)
            jokesArray.toList()
        } else {
            emptyList()
        }
    }

    fun saveJokes(jokes: List<Joke>) {
        val prefs = JokesApplication.appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val json = Gson().toJson(jokes.toTypedArray())
        editor.putString(JOKES_KEY, json)
        editor.apply()
    }
}
