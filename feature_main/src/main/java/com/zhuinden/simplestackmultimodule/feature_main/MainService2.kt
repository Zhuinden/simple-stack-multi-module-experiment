package com.zhuinden.simplestackmultimodule.feature_main

import android.util.Log
import com.zhuinden.simplestack.Backstack
import com.zhuinden.simplestack.ScopedServices
import com.zhuinden.simplestackmoduleexample.navigation.core.ScopedService
import javax.inject.Inject

class MainService2 @Inject constructor(): ScopedService, ScopedServices.HandlesBack {
    private lateinit var backstack: Backstack

    override fun setBackstack(backstack: Backstack) {
        this.backstack = backstack
    }

    override fun onBackEvent(): Boolean {
        return false
    }

    fun logSuccess() {
        Log.i("MainService2", "Service is also loaded successfully [${hashCode()}]")
    }
}