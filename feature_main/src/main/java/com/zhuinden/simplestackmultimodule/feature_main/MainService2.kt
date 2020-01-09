package com.zhuinden.simplestackmultimodule.feature_main

import android.util.Log
import androidx.annotation.Keep
import com.zhuinden.simplestackmoduleexample.common.ScopedService
import javax.inject.Inject

class MainService2 @Inject constructor(): ScopedService {
    fun logSuccess() {
        Log.i("MainService2", "Service is also loaded successfully [${hashCode()}]")
    }
}