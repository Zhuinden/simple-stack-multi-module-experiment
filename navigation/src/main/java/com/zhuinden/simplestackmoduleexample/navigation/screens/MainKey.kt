package com.zhuinden.simplestackmoduleexample.navigation.screens

import com.zhuinden.simplestack.ScopeKey
import com.zhuinden.simplestackmoduleexample.navigation.ServiceRegistry
import com.zhuinden.simplestackmoduleexample.navigation.core.BaseKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainKey(private val placeholder: String = ""): BaseKey(), ScopeKey.Child {
    override fun getParentScopes(): List<String> = ServiceRegistry.resolveServices(this)
}