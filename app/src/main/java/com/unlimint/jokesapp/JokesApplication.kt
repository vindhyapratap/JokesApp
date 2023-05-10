package com.unlimint.jokesapp

import android.app.Application
import android.content.Context
import com.unlimint.jokesapp.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class JokesApplication : Application(){

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        startKoin {
            androidContext(this@JokesApplication)
            modules(appModule)
        }
    }
}