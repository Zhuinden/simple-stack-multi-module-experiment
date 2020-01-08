package com.zhuinden.simplestackmoduleexample.navigation.di;

import com.zhuinden.simplestackmoduleexample.navigation.core.BaseKey;

import dagger.MapKey;

@MapKey
public @interface NavigationKey {
    Class<? extends BaseKey> value();
}