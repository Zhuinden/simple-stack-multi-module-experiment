package com.zhuinden.simplestackmultimodule.application

import android.app.Application
import com.zhuinden.simplestackmultimodule.application.injection.AppComponent
import com.zhuinden.simplestackmultimodule.application.injection.DaggerAppComponent
import javax.inject.Inject

class CustomApplication: Application() {
    private lateinit var component: AppComponent

    @Inject
    lateinit var moduleRegistrations: Map<String, String>

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.create()
        component.inject(this)

        moduleRegistrations
    }

    fun getComponent(): AppComponent = component
}