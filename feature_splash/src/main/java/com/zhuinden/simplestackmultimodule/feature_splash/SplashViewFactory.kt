package com.zhuinden.simplestackmultimodule.feature_splash

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhuinden.simplestackmoduleexample.navigation.core.ViewFactory
import javax.inject.Inject

class SplashViewFactory @Inject constructor(): ViewFactory {
    override fun inflateView(context: Context, container: ViewGroup): View =
        LayoutInflater.from(context).inflate(R.layout.splash_view, container, false)
}