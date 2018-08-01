package com.gu.toolargetool

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.util.*

/**
 * [androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks] implementation that
 * logs information about the saved state of Fragments.
 */
internal class FragmentSavedStateLogger(
        private val fragmentCallback: FragmentCallback
) : FragmentManager.FragmentLifecycleCallbacks() {
    private val savedStates = HashMap<Fragment, Bundle>()

    override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
        savedStates[f] = outState
    }

    override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
        val savedState = savedStates.remove(f)
        if (savedState != null) {
            fragmentCallback.callback(fm, f, savedState)
        }
    }
}
