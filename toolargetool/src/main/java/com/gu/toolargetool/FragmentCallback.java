package com.gu.toolargetool;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * Callback for Fragment on {@link android.os.TransactionTooLargeException}
 */
public interface FragmentCallback {
    void callback(@NonNull FragmentManager fm, @NonNull Fragment f, @NonNull Bundle bundle);
}
