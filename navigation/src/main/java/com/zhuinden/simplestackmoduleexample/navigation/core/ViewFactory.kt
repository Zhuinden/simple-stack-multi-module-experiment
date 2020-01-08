package com.zhuinden.simplestackmoduleexample.navigation.core

import android.content.Context
import android.view.View
import android.view.ViewGroup

interface ViewFactory {
    fun inflateView(context: Context, container: ViewGroup): View
}