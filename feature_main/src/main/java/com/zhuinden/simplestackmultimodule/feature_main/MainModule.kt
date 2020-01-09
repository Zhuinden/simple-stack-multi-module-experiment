package com.zhuinden.simplestackmultimodule.feature_main

import com.zhuinden.simplestackmoduleexample.common.ScopedService
import com.zhuinden.simplestackmoduleexample.common.ServiceKey
import com.zhuinden.simplestackmoduleexample.navigation.core.ViewFactory
import com.zhuinden.simplestackmoduleexample.navigation.di.NavigationKey
import com.zhuinden.simplestackmoduleexample.navigation.screens.MainKey
import com.zhuinden.simplestackmoduleexample.navigation.screens.MainKey2
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet
import javax.inject.Named

@Module(includes = [
    MainServiceModule::class,
    MainService2Module::class,
    MainKeyModule::class,
    MainKey2Module::class
])
abstract class MainModule

@Module
abstract class MainKeyModule {
    @Binds
    @IntoMap
    @NavigationKey(MainKey::class)
    abstract fun viewFactory(viewFactory: MainViewFactory): ViewFactory

    @Binds
    @IntoSet
    @Named("MainKey")
    abstract fun service(clazz: Class<MainService>): Class<out ScopedService>
    
    @Binds
    @IntoSet
    @Named("MainKey")
    abstract fun service2(clazz: Class<MainService2>): Class<out ScopedService>
    
    @Binds
    @IntoMap
    @NavigationKey(MainKey::class)
    abstract fun services(@Named("MainKey") services: @JvmSuppressWildcards Set<Class<out ScopedService>>): @JvmSuppressWildcards Set<Class<out ScopedService>>
}


@Module
abstract class MainKey2Module {
    @Binds
    @IntoMap
    @NavigationKey(MainKey2::class)
    abstract fun viewFactory(viewFactory: MainView2Factory): ViewFactory

    @Binds
    @IntoSet
    @Named("MainKey2")
    abstract fun service2(clazz: Class<MainService2>): Class<out ScopedService>

    @Binds
    @IntoMap
    @NavigationKey(MainKey2::class)
    abstract fun services(@Named("MainKey2") services: @JvmSuppressWildcards Set<Class<out ScopedService>>): @JvmSuppressWildcards Set<Class<out ScopedService>>
}

@Module(includes = [MainServiceModule.ObjectModule::class])
abstract class MainServiceModule {
    @Binds
    @IntoMap
    @ServiceKey(MainService::class)
    abstract fun mainService(mainService: MainService): ScopedService

    @Module
    class ObjectModule {
        @Provides
        fun service(): Class<MainService> = MainService::class.java
    }
}

@Module(includes = [MainService2Module.ObjectModule::class])
abstract class MainService2Module {
    @Binds
    @IntoMap
    @ServiceKey(MainService2::class)
    abstract fun mainService2(mainService: MainService2): ScopedService

    @Module
    class ObjectModule {
        @Provides
        fun service2(): Class<MainService2> = MainService2::class.java
    }
}