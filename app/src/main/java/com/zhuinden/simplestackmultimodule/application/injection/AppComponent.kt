package com.zhuinden.simplestackmultimodule.application.injection

import com.zhuinden.simplestackmultimodule.application.CustomApplication
import com.zhuinden.simplestackmultimodule.application.MainActivity
import com.zhuinden.simplestackmultimodule.feature_main.MainModule
import com.zhuinden.simplestackmultimodule.feature_splash.SplashModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SplashModule::class, MainModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(customApplication: CustomApplication)
}