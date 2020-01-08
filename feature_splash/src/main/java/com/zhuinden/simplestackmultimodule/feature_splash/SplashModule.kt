package com.zhuinden.simplestackmultimodule.feature_splash

import com.zhuinden.simplestackmoduleexample.navigation.core.ViewFactory
import com.zhuinden.simplestackmoduleexample.navigation.di.NavigationKey
import com.zhuinden.simplestackmoduleexample.navigation.screens.SplashKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SplashModule {
    @Binds
    @IntoMap
    @NavigationKey(SplashKey::class)
    abstract fun viewFactory(splashViewFactory: SplashViewFactory): ViewFactory
}