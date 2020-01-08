package com.zhuinden.simplestackmultimodule.feature_splash

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.widget.FrameLayout
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestack.navigator.Navigator
import com.zhuinden.simplestackmoduleexample.navigation.backstack
import com.zhuinden.simplestackmoduleexample.navigation.replaceHistory
import com.zhuinden.simplestackmoduleexample.navigation.screens.MainKey

class SplashView: FrameLayout {
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    private val delayProvider = Handler()

    override fun onFinishInflate() {
        super.onFinishInflate()

        val backstack = backstack

        delayProvider.postDelayed({
            backstack.replaceHistory(StateChange.FORWARD, MainKey())
        }, 1500L)
    }
}