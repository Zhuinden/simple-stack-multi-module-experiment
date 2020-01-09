package com.zhuinden.simplestackmultimodule.application

import android.app.Application
import com.zhuinden.simplestackmoduleexample.common.ScopedService
import com.zhuinden.simplestackmoduleexample.navigation.ServiceRegistry
import com.zhuinden.simplestackmoduleexample.navigation.core.BaseKey
import com.zhuinden.simplestackmultimodule.application.injection.AppComponent
import com.zhuinden.simplestackmultimodule.application.injection.DaggerAppComponent
import javax.inject.Inject

class CustomApplication: Application() {
    private lateinit var component: AppComponent

    @Inject
    lateinit var keyMapping: Map<Class<out BaseKey>, @JvmSuppressWildcards Set<Class<out ScopedService>>>

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.create()
        component.inject(this)

        ServiceRegistry.keyMapping = keyMapping // setup auto-scoping
    }

    fun getComponent(): AppComponent = component
}