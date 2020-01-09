package com.zhuinden.simplestackmultimodule.feature_main

import android.util.Log
import androidx.annotation.Keep
import com.zhuinden.simplestackmoduleexample.common.ScopedService
import javax.inject.Inject

class MainService @Inject constructor(): ScopedService {
    fun logSuccess() {
        Log.i("MainService", "Service is loaded successfully")
    }
}