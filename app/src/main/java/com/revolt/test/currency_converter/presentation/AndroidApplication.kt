package com.revolt.test.currency_converter.presentation

import android.app.Application
import com.revolt.test.currency_converter.di.moduleList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin{
            // Android context
            androidContext(this@AndroidApplication)
            // modules
            modules(modules = moduleList)
        }
    }
}