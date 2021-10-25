package com.marcelo.nybooks.app

import android.app.Application
import com.marcelo.nybooks.directory.networkModule
import com.marcelo.nybooks.directory.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NyBooksApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@NyBooksApp)

            modules(listOf(
                viewModule,
                networkModule
            ))
        }
    }
}