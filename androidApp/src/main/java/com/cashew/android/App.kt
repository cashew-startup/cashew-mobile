package com.cashew.android

import android.app.Application
import android.content.Context
import com.cashew.core.ComponentFactory
import org.koin.core.Koin

class App : Application() {

    lateinit var koin: Koin

    override fun onCreate() {
        super.onCreate()
        koin = createKoin()
    }

    private fun createKoin() = Koin().apply {
        loadModules(modules)
        declare(this@App as Context)
        declare(this@App as Application)
        declare(ComponentFactory(koin = this))
    }
}

val Application.koin get() = (this as App).koin