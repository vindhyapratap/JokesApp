package com.unlimint.jokesapp.module

import com.unlimint.jokesapp.Constants
import com.unlimint.jokesapp.repositories.JokeRepository
import com.unlimint.jokesapp.repositories.JokeRepositoryImpl
import com.unlimint.jokesapp.repositories.JokeService
import com.unlimint.jokesapp.viewmodel.JokeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    // Define the ViewModel
    viewModel { JokeViewModel(get()) }

    // Define the JokeRepository
    single<JokeRepository> { JokeRepositoryImpl(get()) }

    // Define the JokeService
    single<JokeService> { createJokeService() }
}

private fun createJokeService(): JokeService {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JokeService::class.java)
}
