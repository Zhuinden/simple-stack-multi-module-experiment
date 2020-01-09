package com.zhuinden.simplestackmultimodule.feature_main

import com.zhuinden.simplestackmoduleexample.common.ScopedService
import com.zhuinden.simplestackmoduleexample.common.ServiceKey
import com.zhuinden.simplestackmoduleexample.navigation.core.ViewFactory
import com.zhuinden.simplestackmoduleexample.navigation.di.NavigationKey
import com.zhuinden.simplestackmoduleexample.navigation.screens.MainKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet
import javax.inject.Named

@Module(includes = [MainServiceModule::class])
abstract class MainModule {
    @Binds
    @IntoMap
    @NavigationKey(MainKey::class)
    abstract fun viewFactory(viewFactory: MainViewFactory): ViewFactory

    @Binds
    @IntoMap
    @ServiceKey(MainService::class)
    abstract fun mainService(mainService: MainService): ScopedService

    @Binds
    @IntoMap
    @NavigationKey(MainKey::class)
    abstract fun services(@Named("main") services: @JvmSuppressWildcards Set<Class<out ScopedService>>): @JvmSuppressWildcards Set<Class<out ScopedService>>

    @Binds
    @IntoSet
    @Named("main")
    abstract fun service(clazz: Class<MainService>): Class<out ScopedService>
}

@Module
class MainServiceModule {
    @Provides
    fun service(): Class<MainService> = MainService::class.java
}