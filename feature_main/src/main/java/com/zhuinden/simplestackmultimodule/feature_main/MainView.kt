package com.zhuinden.simplestackmultimodule.feature_main

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.zhuinden.simplestackmoduleexample.common.onClick
import com.zhuinden.simplestackmoduleexample.common.waitForMeasure
import com.zhuinden.simplestackmoduleexample.navigation.scopedService
import kotlinx.android.synthetic.main.main_view.view.*

class MainView: FrameLayout {
    interface ActionHandler {
        fun onMainClicked()
    }

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    private val actionHandler by scopedService<ActionHandler>()

    override fun onFinishInflate() {
        super.onFinishInflate()

        buttonMain.onClick { _ ->
            actionHandler.onMainClicked()
        }

        waitForMeasure { _, _, _ ->
            // .
        }
    }
}