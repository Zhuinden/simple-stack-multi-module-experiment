package com.zhuinden.simplestackmultimodule.feature_main

import android.util.Log
import com.zhuinden.simplestack.Backstack
import com.zhuinden.simplestackmoduleexample.navigation.core.ScopedService
import com.zhuinden.simplestackmoduleexample.navigation.screens.MainKey2
import javax.inject.Inject

class MainService @Inject constructor(): ScopedService, MainView.ActionHandler {
    private lateinit var backstack: Backstack

    override fun setBackstack(backstack: Backstack) {
        this.backstack = backstack
    }

    override fun onMainClicked() {
        backstack.goTo(MainKey2())
    }

    fun logSuccess() {
        Log.i("MainService", "Service is loaded successfully [${hashCode()}]")
    }
}