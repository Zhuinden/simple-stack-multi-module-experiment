package com.zhuinden.simplestackmoduleexample.navigation

import com.zhuinden.simplestackmoduleexample.common.ScopedService
import com.zhuinden.simplestackmoduleexample.navigation.core.BaseKey
import java.util.*

object ServiceRegistry {
    val keyMapping: MutableMap<Class<out BaseKey>, @JvmSuppressWildcards MutableSet<Class<out ScopedService>>> = mutableMapOf()

    fun resolveServices(key: BaseKey): List<String> {
        if(!keyMapping.containsKey(key.javaClass)) {
            return Collections.emptyList()
        }
        val serviceClasses = keyMapping.get(key.javaClass)
        return serviceClasses!!.map { it.name }
    }

    inline fun <reified S: ScopedService> addServiceForKey(classKey: Class<out BaseKey>) {
        if(!keyMapping.containsKey(classKey)) {
            keyMapping.put(classKey, mutableSetOf())
        }
        val set = keyMapping.getValue(classKey)
        set.add(S::class.java)
    }

    inline fun registerServices(classKey: Class<out BaseKey>, builder: RegistryBuilder.() -> Unit) {
        RegistryBuilder(classKey).apply(builder)
    }

    class RegistryBuilder(val classKey: Class<out BaseKey>) {
        inline fun <reified S: ScopedService> add() {
            addServiceForKey<S>(classKey)
        }
    }
}