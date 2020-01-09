package com.zhuinden.simplestackmultimodule.application.injection;

import com.zhuinden.simplestackmoduleexample.common.ScopedService;
import com.zhuinden.simplestackmoduleexample.navigation.core.BaseKey;
import com.zhuinden.simplestackmoduleexample.navigation.core.ViewFactory;
import com.zhuinden.simplestackmoduleexample.navigation.screens.MainKey;
import com.zhuinden.simplestackmoduleexample.navigation.screens.SplashKey;
import com.zhuinden.simplestackmultimodule.application.CustomApplication;
import com.zhuinden.simplestackmultimodule.application.CustomApplication_MembersInjector;
import com.zhuinden.simplestackmultimodule.application.MainActivity;
import com.zhuinden.simplestackmultimodule.application.MainActivity_MembersInjector;
import com.zhuinden.simplestackmultimodule.application.ServiceProvider;
import com.zhuinden.simplestackmultimodule.feature_main.MainModule;

import com.zhuinden.simplestackmultimodule.feature_main.MainService;
import com.zhuinden.simplestackmultimodule.feature_main.MainService_Factory;
import com.zhuinden.simplestackmultimodule.feature_main.MainViewFactory;
import com.zhuinden.simplestackmultimodule.feature_splash.SplashViewFactory;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.inject.Provider;

@SuppressWarnings({
        "unchecked",
        "rawtypes"
})
public final class DaggerAppComponent implements AppComponent {
    private DaggerAppComponent() {

    }

    public static Builder builder() {
        return new Builder();
    }

    public static AppComponent create() {
        return new Builder().build();
    }

    private Map<Class<? extends BaseKey>, ViewFactory> getMapOfClassOfAndViewFactory() {
        return MapBuilder.<Class<? extends BaseKey>, ViewFactory>newMapBuilder(2).put(SplashKey.class, new SplashViewFactory()).put(MainKey.class, new MainViewFactory()).build();}

    private Map<Class<? extends ScopedService>, Provider<ScopedService>> getMapOfClassOfAndProviderOfScopedService(
    ) {
        return Collections.<Class<? extends ScopedService>, Provider<ScopedService>>singletonMap(MainService.class, (Provider) MainService_Factory.create());}

    private ServiceProvider getServiceProvider() {
        return new ServiceProvider(getMapOfClassOfAndProviderOfScopedService());}

    private Set<Class<? extends ScopedService>> getNamedSetOfClassOf() {
        return Collections.<Class<? extends ScopedService>>singleton(MainModule.service());}

    private Map<Class<? extends BaseKey>, Set<Class<? extends ScopedService>>> getMapOfClassOfAndSetOfClassOf(
    ) {
        return Collections.<Class<? extends BaseKey>, Set<Class<? extends ScopedService>>>singletonMap(MainKey.class, getNamedSetOfClassOf());}

    @Override
    public void inject(MainActivity mainActivity) {
        injectMainActivity(mainActivity);}

    @Override
    public void inject(CustomApplication customApplication) {
        injectCustomApplication(customApplication);}

    private MainActivity injectMainActivity(MainActivity instance) {
        MainActivity_MembersInjector.injectViewFactories(instance, getMapOfClassOfAndViewFactory());
        MainActivity_MembersInjector.injectServiceProvider(instance, getServiceProvider());
        return instance;
    }

    private CustomApplication injectCustomApplication(CustomApplication instance) {
        CustomApplication_MembersInjector.injectKeyMapping(instance, getMapOfClassOfAndSetOfClassOf());
        return instance;
    }

    public static final class Builder {
        private Builder() {
        }

        /**
         * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
         */
        @Deprecated
        public Builder mainModule(MainModule mainModule) {
            Preconditions.checkNotNull(mainModule);
            return this;
        }

        public AppComponent build() {
            return new DaggerAppComponent();
        }
    }
}
