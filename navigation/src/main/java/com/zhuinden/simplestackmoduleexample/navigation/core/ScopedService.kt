package com.zhuinden.simplestackmoduleexample.navigation.core

import com.zhuinden.simplestack.Backstack

interface ScopedService {
    fun setBackstack(backstack: Backstack)
}