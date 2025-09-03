package com.example.mychatapp.app

import android.app.Application
import com.example.mychatapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BaseApp: Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            module { appModule }
            androidContext(applicationContext)
        }

    }
}