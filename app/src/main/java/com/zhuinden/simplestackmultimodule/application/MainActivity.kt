package com.zhuinden.simplestackmultimodule.application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zhuinden.simplestack.History
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestack.StateChanger
import com.zhuinden.simplestack.navigator.Navigator
import com.zhuinden.simplestack.navigator.ViewChangeHandler
import com.zhuinden.simplestack.navigator.changehandlers.FadeViewChangeHandler
import com.zhuinden.simplestack.navigator.changehandlers.SegueViewChangeHandler
import com.zhuinden.simplestackmoduleexample.navigation.core.BaseKey
import com.zhuinden.simplestackmoduleexample.navigation.core.ViewFactory
import com.zhuinden.simplestackmoduleexample.navigation.screens.SplashKey
import com.zhuinden.simplestackmultimodule.R
import com.zhuinden.simplestackmultimodule.application.injection.AppComponent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), StateChanger {
    private lateinit var component: AppComponent

    @Inject
    lateinit var viewFactories: Map<Class<out BaseKey>, @JvmSuppressWildcards ViewFactory>

    @Inject
    lateinit var serviceProvider: ServiceProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component = (application as CustomApplication).getComponent()
        component.inject(this)

        Navigator.configure()
            .setStateChanger(this)
            .setScopedServices(serviceProvider)
            .install(this, root, History.of(SplashKey()))
    }

    override fun onBackPressed() {
        if (!Navigator.onBackPressed(this)) {
            super.onBackPressed()
        }
    }

    override fun handleStateChange(stateChange: StateChange, completionCallback: StateChanger.Callback) {
        if (stateChange.isTopNewKeyEqualToPrevious) {
            completionCallback.stateChangeComplete()
            return
        }

        val previousView = root.getChildAt(0)
        Navigator.persistViewToState(previousView)

        val newKey = stateChange.topNewKey<BaseKey>()

        val viewFactory = viewFactories.get(newKey.javaClass)!!
        val newView = viewFactory.inflateView(stateChange.createContext(this, newKey), root)
        Navigator.restoreViewFromState(newView)

        if (previousView == null) {
            root.removeView(previousView)
            root.addView(newView)
            completionCallback.stateChangeComplete()
            return
        }

        val viewChangeHandler: ViewChangeHandler = when {
            stateChange.direction == StateChange.REPLACE -> FadeViewChangeHandler()
            else -> SegueViewChangeHandler()
        }

        viewChangeHandler.performViewChange(root, previousView, newView, stateChange.direction) {
            completionCallback.stateChangeComplete()
        }
    }
}