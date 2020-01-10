package com.zhuinden.simplestackmultimodule.feature_main

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.zhuinden.simplestackmoduleexample.common.waitForMeasure
import com.zhuinden.simplestackmoduleexample.navigation.scopedService

class MainView2: FrameLayout {
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