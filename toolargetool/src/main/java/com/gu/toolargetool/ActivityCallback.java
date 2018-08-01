package com.gu.toolargetool;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;

/**
 * Callback for Activity on {@link android.os.TransactionTooLargeException}
 */
public interface ActivityCallback {
    void callback(@NonNull Activity activity, @NonNull Bundle bundle);
}
