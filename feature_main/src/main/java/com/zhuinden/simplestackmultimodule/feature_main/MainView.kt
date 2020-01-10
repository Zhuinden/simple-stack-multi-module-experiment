package com.zhuinden.simplestackmultimodule.feature_main

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.zhuinden.simplestackmoduleexample.common.onClick
import com.zhuinden.simplestackmoduleexample.common.waitForMeasure
import com.zhuinden.simplestackmoduleexample.navigation.ServiceRegistry
import com.zhuinden.simplestackmoduleexample.navigation.backstack
import com.zhuinden.simplestackmoduleexample.navigation.scopedService
import com.zhuinden.simplestackmoduleexample.navigation.screens.MainKey
import com.zhuinden.simplestackmoduleexample.navigation.screens.MainKey2
import kotlinx.android.synthetic.main.main_view.view.*

class MainView: FrameLayout {
    companion object { // this is a workaround for `static {}` initializers not running until a class is invoked.
        init {
            ServiceRegistry.registerServices(MainKey::class.java) {
                add<MainService>()
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

        buttonMain.onClick { _ ->
            backstack.goTo(MainKey2())
        }

        waitForMeasure { _, _, _ ->
            val service = mainService
            service.logSuccess()

            val service2 = mainService2
            service2.logSuccess()
        }
    }
}