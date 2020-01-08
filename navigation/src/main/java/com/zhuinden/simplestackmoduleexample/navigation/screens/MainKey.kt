package com.zhuinden.simplestackmoduleexample.navigation.screens

import com.zhuinden.simplestack.ScopeKey
import com.zhuinden.simplestackmoduleexample.navigation.core.BaseKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainKey(private val placeholder: String = ""): BaseKey(), ScopeKey.Child {
    override fun getParentScopes(): List<String> = listOf(
        "com.zhuinden.simplestackmultimodule.feature_main.MainService" // TODO: how will I see this?
    )
}