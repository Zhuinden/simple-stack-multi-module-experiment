package com.zhuinden.simplestackmoduleexample.common;

import dagger.MapKey;

@MapKey
public @interface ServiceKey {
    Class<? extends ScopedService> value();
}