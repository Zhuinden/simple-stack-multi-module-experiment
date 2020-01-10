package com.zhuinden.simplestackmultimodule.feature_main

import com.zhuinden.simplestackmoduleexample.common.ScopedService
import com.zhuinden.simplestackmoduleexample.common.ServiceKey
import com.zhuinden.simplestackmoduleexample.navigation.ServiceRegistry
import com.zhuinden.simplestackmoduleexample.navigation.core.ViewFactory
import com.zhuinden.simplestackmoduleexample.navigation.di.NavigationKey
import com.zhuinden.simplestackmoduleexample.navigation.screens.MainKey
import com.zhuinden.simplestackmoduleexample.navigation.screens.MainKey2
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module(
    includes = [
        MainServiceModule::class,
        MainService2Module::class,
        MainKeyModule::class,
        MainKey2Module::class
    ]
)
class MainModule {
    @Provides
    @IntoMap
    @StringKey("main")
    fun initializer(): String = "main"

    companion object {
        init {
            ServiceRegistry.registerServices(MainKey::class.java) {
                add<MainService>()
                add<MainService2>()
            }

            ServiceRegistry.registerServices(MainKey2::class.java) {
                add<MainService2>()
            }
        }
    }
}

@Module
abstract class MainKeyModule {
    @Binds
    @IntoMap
    @NavigationKey(MainKey::class)
    abstract fun viewFactory(viewFactory: MainViewFactory): ViewFactory
}


@Module
abstract class MainKey2Module {
    @Binds
    @IntoMap
    @NavigationKey(MainKey2::class)
    abstract fun viewFactory(viewFactory: MainView2Factory): ViewFactory
}

@Module
abstract class MainServiceModule {
    @Binds
    @IntoMap
    @ServiceKey(MainService::class)
    abstract fun mainService(mainService: MainService): ScopedService
}

@Module
abstract class MainService2Module {
    @Binds
    @IntoMap
    @ServiceKey(MainService2::class)
    abstract fun mainService2(mainService: MainService2): ScopedService
}