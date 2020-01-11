package com.zhuinden.simplestackmultimodule.feature_main

// this is only needed because Dagger generates invalid code since 2.14
// (see https://github.com/google/dagger/issues/1714)
internal class MainModule_InitializerFactory private constructor() {
    companion object {
        @JvmStatic
        fun initializer(mainModule: MainModule): String = mainModule.initializer()
    }
}