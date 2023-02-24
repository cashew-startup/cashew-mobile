package com.cashew.android

import android.app.Application
import android.content.Context
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import com.cashew.core.ComponentFactory
import org.koin.core.Koin

class App : Application(), ImageLoaderFactory {

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

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()
    }
}

val Application.koin get() = (this as App).koin