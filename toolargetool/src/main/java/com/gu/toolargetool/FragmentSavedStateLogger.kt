package com.gu.toolargetool

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.util.*

/**
 * [androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks] implementation that
 * logs information about the saved state of Fragments.
 */
class FragmentSavedStateLogger internal constructor(private val fragmentCallback: TooLargeLoggerCallback) : FragmentManager.FragmentLifecycleCallbacks() {
    private val savedStates = HashMap<Fragment, Bundle>()

    override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
        savedStates[f] = outState
    }

    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
        val savedState = savedStates.remove(f)
        if (savedState != null) {
            var message = f.javaClass.simpleName + ".onSaveInstanceState wrote: " + LoggingHelper().bundleBreakdown(savedState)
            f.arguments?.let {
                message += "\n* fragment arguments = " + LoggingHelper().bundleBreakdown(it)
            }
            fragmentCallback.log(message)
        }
    }
}
