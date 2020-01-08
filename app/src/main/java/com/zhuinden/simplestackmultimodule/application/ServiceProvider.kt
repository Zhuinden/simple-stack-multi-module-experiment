package com.zhuinden.simplestackmultimodule.application

import com.zhuinden.simplestack.ScopedServices
import com.zhuinden.simplestack.ServiceBinder
import com.zhuinden.simplestackmoduleexample.common.ScopedService
import javax.inject.Inject
import javax.inject.Provider

class ServiceProvider @Inject constructor(
    private val services: Map<Class<out ScopedService>, @JvmSuppressWildcards Provider<ScopedService>>
) : ScopedServices {
    override fun bindServices(serviceBinder: ServiceBinder) {
        val scopeTag = serviceBinder.scopeTag

        val serviceClass = services.keys.firstOrNull { clazz -> clazz.name == scopeTag }
        if (serviceClass != null) {
            serviceBinder.addService(serviceClass.name, services.get(serviceClass)!!.get())
        }
    }
}