package com.zhuinden.simplestackmultimodule.feature_main

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.zhuinden.simplestackmoduleexample.common.waitForMeasure
import com.zhuinden.simplestackmoduleexample.navigation.ServiceRegistry
import com.zhuinden.simplestackmoduleexample.navigation.scopedService
import com.zhuinden.simplestackmoduleexample.navigation.screens.MainKey
import com.zhuinden.simplestackmoduleexample.navigation.screens.MainKey2

class MainView2: FrameLayout {
    companion object { // this is a workaround for `static {}` initializers not running until a class is invoked.
        init {
            ServiceRegistry.registerServices(MainKey2::class.java) {
                add<MainService2>()
            }
        }
    }

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    private val mainService by scopedService<MainService>()
    private val mainService2 by scopedService<MainService2>()

    override fun onFinishInflate() {
        super.onFinishInflate()

        waitForMeasure { _, _, _ ->
            val service2 = mainService2
            service2.logSuccess()

            val service = mainService
            service.logSuccess()
        }
    }
}