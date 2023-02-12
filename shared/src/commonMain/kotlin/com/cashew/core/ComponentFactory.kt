package com.cashew.core

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

class ComponentFactory(private val koin: Koin): KoinComponent {

    override fun getKoin(): Koin {
        return koin
    }
}