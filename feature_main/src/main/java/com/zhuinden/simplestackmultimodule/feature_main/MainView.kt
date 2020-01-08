package com.zhuinden.simplestackmultimodule.feature_main

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.zhuinden.simplestackmoduleexample.common.waitForMeasure
import com.zhuinden.simplestackmoduleexample.navigation.backstack
import com.zhuinden.simplestackmoduleexample.navigation.lookup

class MainView: FrameLayout {
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    private val mainService by lazy { backstack.lookup<MainService>() }

    override fun onFinishInflate() {
        super.onFinishInflate()

        waitForMeasure { _, _, _ ->
            val service = mainService
            service.logSuccess()
        }
    }
}