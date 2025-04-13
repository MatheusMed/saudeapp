package com.example.saudeocaraapp

import android.app.Application
import com.example.saudeocaraapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class SaudeOcaraApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@SaudeOcaraApplication)
            modules(appModule)
        }

    }
}