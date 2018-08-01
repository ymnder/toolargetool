package com.gu.toolargetool

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import java.util.*

/**
 * [android.app.Application.ActivityLifecycleCallbacks] implementation that logs information
 * about the saved state of Activities.
 */
internal class ActivitySavedStateLogger(
        private val activityCallback: ActivityCallback,
        fragmentCallback: FragmentCallback?
) : Application.ActivityLifecycleCallbacks {

    private val fragmentLogger: FragmentSavedStateLogger? = if (fragmentCallback != null) FragmentSavedStateLogger(fragmentCallback) else null
    private val savedStates = HashMap<Activity, Bundle>()


    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle) {
        if (activity is FragmentActivity && fragmentLogger != null) {
            activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(fragmentLogger, true)
        }
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (activity is FragmentActivity && fragmentLogger != null) {
            activity.supportFragmentManager
                    .unregisterFragmentLifecycleCallbacks(fragmentLogger)
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        savedStates[activity] = outState
    }

    override fun onActivityStopped(activity: Activity) {
        val savedState = savedStates.remove(activity)
        savedState?.let {
            activityCallback.callback(activity, it)
        }
    }

    override fun onActivityPaused(p0: Activity?) {
        //NOP
    }

    override fun onActivityResumed(p0: Activity?) {
        //NOP
    }

    override fun onActivityStarted(p0: Activity?) {
        //NOP
    }
}
