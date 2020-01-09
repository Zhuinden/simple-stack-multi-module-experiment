package com.zhuinden.simplestackmoduleexample.navigation

import android.app.Activity
import android.view.View
import com.zhuinden.simplestack.Backstack
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestack.navigator.Navigator
import com.zhuinden.simplestackmoduleexample.common.ScopedService
import com.zhuinden.simplestackmoduleexample.navigation.core.BaseKey

// navigation helpers
val View.backstack: Backstack
    get() = Navigator.getBackstack(context)

fun <T: BaseKey> View.getKey(): T = Backstack.getKey(context)

val Activity.backstack: Backstack
    get() = Navigator.getBackstack(this)

fun Backstack.replaceHistory(direction: Int, vararg keys: Any) {
    setHistory(keys.toList(), direction)
}

// service helpers
inline fun <reified T: ScopedService> Backstack.lookup(): T = lookupService(T::class.java.name)

inline fun <reified T: ScopedService> View.scopedService(): Lazy<T> = lazy { backstack.lookup<T>() }