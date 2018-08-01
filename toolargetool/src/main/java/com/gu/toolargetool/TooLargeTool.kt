package com.gu.toolargetool

import android.app.Application

/**
 * A collection of helper methods to assist you in debugging crashes due to
 * [android.os.TransactionTooLargeException]
 * <p>
 * The easiest way to use this class is to call TooLargeTool#startLogging(Application) in your app's
 * Application#onCreate() method.
 */
class TooLargeTool {

    /**
     * Start logging information about all of the state saved by Activities and Fragments.
     *
     * @param application for register activity lifecycle to log
     * @param param for logging params
     */
    @JvmOverloads
    fun startLogging(
            application: Application,
            param: LoggerParam = LoggerParam.Builder().build()
    ) {
        application.registerActivityLifecycleCallbacks(ActivitySavedStateLogger(param.activityCallback, param.fragmentCallback))
    }

}