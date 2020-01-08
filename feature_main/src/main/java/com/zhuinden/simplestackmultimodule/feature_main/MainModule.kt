package com.zhuinden.simplestackmultimodule.feature_main

import com.zhuinden.simplestackmoduleexample.common.ScopedService
import com.zhuinden.simplestackmoduleexample.common.ServiceKey
import com.zhuinden.simplestackmoduleexample.navigation.core.ViewFactory
import com.zhuinden.simplestackmoduleexample.navigation.di.NavigationKey
import com.zhuinden.simplestackmoduleexample.navigation.screens.MainKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
abstract class MainModule {
    @Binds
    @IntoMap
    @NavigationKey(MainKey::class)
    abstract fun viewFactory(viewFactory: MainViewFactory): ViewFactory

    @Binds
    @IntoMap
    @ServiceKey(MainService::class)
    abstract fun mainService(mainService: MainService): ScopedService
}