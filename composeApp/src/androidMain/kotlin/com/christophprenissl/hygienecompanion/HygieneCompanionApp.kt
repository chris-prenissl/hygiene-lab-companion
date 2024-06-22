package com.christophprenissl.hygienecompanion

import android.app.Application
import com.christophprenissl.hygienecompanion.di.appModule
import com.christophprenissl.hygienecompanion.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HygieneCompanionApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@HygieneCompanionApp)
            modules(appModule, viewModelModule)
        }
    }
}