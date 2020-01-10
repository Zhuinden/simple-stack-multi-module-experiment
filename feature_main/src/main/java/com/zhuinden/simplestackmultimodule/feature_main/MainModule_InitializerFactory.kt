package com.zhuinden.simplestackmultimodule.feature_main

internal class MainModule_InitializerFactory private constructor() {
    companion object {
        @JvmStatic
        fun initializer(mainModule: MainModule): String = mainModule.initializer()
    }
}