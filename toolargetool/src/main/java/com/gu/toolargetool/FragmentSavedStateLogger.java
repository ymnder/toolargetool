package com.gu.toolargetool;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks} implementation that
 * logs information about the saved state of Fragments.
 */
public class FragmentSavedStateLogger extends FragmentManager.FragmentLifecycleCallbacks {

    @NonNull
    private final TooLargeLoggerCallback fragmentCallback;
    @NonNull
    private final Map<Fragment, Bundle> savedStates = new HashMap<>();

    FragmentSavedStateLogger(@NonNull TooLargeLoggerCallback fragmentCallback) {
        this.fragmentCallback = fragmentCallback;
    }

    @Override
    public void onFragmentSaveInstanceState(FragmentManager fm, Fragment f, Bundle outState) {
        savedStates.put(f, outState);
    }

    @Override
    public void onFragmentStopped(FragmentManager fm, Fragment f) {
        Bundle savedState = savedStates.remove(f);
        if (savedState != null) {
            String message = f.getClass().getSimpleName() + ".onSaveInstanceState wrote: " + TooLargeTool.bundleBreakdown(savedState);
            if (f.getArguments() != null) {
                message += "\n* fragment arguments = " + TooLargeTool.bundleBreakdown(f.getArguments());
            }
            fragmentCallback.log(message);
        }
    }
}
