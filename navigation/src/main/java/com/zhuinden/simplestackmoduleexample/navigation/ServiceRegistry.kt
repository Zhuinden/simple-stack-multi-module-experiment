package com.zhuinden.simplestackmoduleexample.navigation

import com.zhuinden.simplestackmoduleexample.common.ScopedService
import com.zhuinden.simplestackmoduleexample.navigation.core.BaseKey
import java.util.*

object ServiceRegistry {
    lateinit var keyMapping: Map<Class<out BaseKey>, @JvmSuppressWildcards Set<Class<out ScopedService>>>

    fun resolveServices(key: BaseKey): List<String> {
        if(!keyMapping.containsKey(key.javaClass)) {
            return Collections.emptyList()
        }
        val serviceClasses = keyMapping.get(key.javaClass)
        val serviceNames = serviceClasses!!.map { it.name }
        return serviceNames
    }
}