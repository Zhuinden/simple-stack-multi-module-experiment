package com.zhuinden.simplestackmultimodule.application

import com.zhuinden.simplestack.ScopedServices
import com.zhuinden.simplestack.ServiceBinder
import com.zhuinden.simplestackmoduleexample.navigation.ServiceRegistry
import com.zhuinden.simplestackmoduleexample.navigation.core.BaseKey
import com.zhuinden.simplestackmoduleexample.navigation.core.ScopedService
import javax.inject.Inject
import javax.inject.Provider

class ServiceProvider @Inject constructor(
    private val services: Map<Class<out ScopedService>, @JvmSuppressWildcards Provider<ScopedService>>
) : ScopedServices {
    override fun bindServices(serviceBinder: ServiceBinder) {
        val scopeTag = serviceBinder.scopeTag

        val serviceClass = services.keys.firstOrNull { clazz -> clazz.name == scopeTag }
        if (serviceClass != null) {
            val service = services[serviceClass]!!.get().apply {
                setBackstack(serviceBinder.backstack)
            }
            serviceBinder.addService(serviceClass.name, service)

            val aliases = ServiceRegistry.resolveAliases(serviceBinder.getKey<BaseKey>().javaClass, serviceClass)
            for (aliasClass in aliases) {
                serviceBinder.addAlias(aliasClass.java.name, service)
            }
        }
    }
}