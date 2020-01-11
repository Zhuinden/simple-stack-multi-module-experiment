package com.zhuinden.simplestackmoduleexample.navigation

import com.zhuinden.simplestackmoduleexample.navigation.core.ScopedService
import com.zhuinden.simplestackmoduleexample.navigation.core.BaseKey
import java.util.*
import kotlin.reflect.KClass

object ServiceRegistry {
    @PublishedApi
    internal val keyMapping: MutableMap<Class<out BaseKey>, @JvmSuppressWildcards MutableSet<Class<out ScopedService>>> = mutableMapOf()

    @PublishedApi
    internal val aliasMapping: MutableMap<Class<out BaseKey>, @JvmSuppressWildcards MutableMap<Class<out ScopedService>, MutableSet<KClass<*>>>> = mutableMapOf()

    @PublishedApi
    internal inline fun <reified S: ScopedService> addServiceForKey(classKey: Class<out BaseKey>) {
        if(!keyMapping.containsKey(classKey)) {
            keyMapping[classKey] = mutableSetOf()
        }
        val set = keyMapping.getValue(classKey)
        set.add(S::class.java)
    }

    @PublishedApi
    internal inline fun <reified S: ScopedService> addAliasForService(classKey: Class<out BaseKey>, aliasClass: KClass<*>) {
        if(!aliasMapping.containsKey(classKey)) {
            aliasMapping[classKey] = mutableMapOf()
        }
        val map = aliasMapping.getValue(classKey)

        val serviceClass = S::class.java
        if(!map.containsKey(serviceClass)) {
            map[serviceClass] = mutableSetOf()
        }
        map.getValue(serviceClass).add(aliasClass)
    }

    inline fun <reified T: BaseKey> registerServices(builder: RegistryBuilder.() -> Unit) {
        RegistryBuilder(T::class.java).apply(builder)
    }

    fun resolveServices(key: BaseKey): List<String> {
        if(!keyMapping.containsKey(key.javaClass)) {
            return Collections.emptyList()
        }
        val serviceClasses = keyMapping.get(key.javaClass)
        return serviceClasses!!.map { it.name }
    }

    fun resolveAliases(key: Class<out BaseKey>, serviceClass: Class<out ScopedService>): List<KClass<*>> {
        val aliasesForKey = aliasMapping[key] ?: return Collections.emptyList()
        val aliasesForService = aliasesForKey[serviceClass] ?: return Collections.emptyList()
        return aliasesForService.toList()
    }

    class RegistryBuilder(val classKey: Class<out BaseKey>) {
        inline fun <reified S: ScopedService> add() {
            addServiceForKey<S>(classKey)
        }

        inline fun <reified S: ScopedService> bindAs(alias: KClass<*>) {
            addAliasForService<S>(classKey, alias)
        }
    }
}