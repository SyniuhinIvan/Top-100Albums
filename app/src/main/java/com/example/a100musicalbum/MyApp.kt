package com.example.a100musicalbum

import android.app.Application
import com.example.a100musicalbum.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import java.util.concurrent.TimeUnit

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        /* TODO: тимбер */
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(appModule))
        }

    }

}