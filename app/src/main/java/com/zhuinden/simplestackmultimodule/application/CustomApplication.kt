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

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.create()
        component.inject(this)
    }

    fun getComponent(): AppComponent = component
}