package com.zhuinden.simplestackmultimodule.application

import android.app.Application
import com.zhuinden.simplestackmultimodule.application.injection.AppComponent
import com.zhuinden.simplestackmultimodule.application.injection.DaggerAppComponent

class CustomApplication: Application() {
    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.create()
    }

    fun getComponent(): AppComponent = component
}