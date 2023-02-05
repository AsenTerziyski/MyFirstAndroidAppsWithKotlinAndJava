package com.example.mychucknoris

import android.app.Application
import com.example.mychucknoris.di.di
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MyApplication)
            modules(di)
        }
    }
}