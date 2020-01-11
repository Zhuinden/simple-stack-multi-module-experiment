package com.zhuinden.simplestackmoduleexample.navigation.di;

import com.zhuinden.simplestackmoduleexample.navigation.core.ScopedService;

import dagger.MapKey;

@MapKey
public @interface ServiceKey {
    Class<? extends ScopedService> value();
}