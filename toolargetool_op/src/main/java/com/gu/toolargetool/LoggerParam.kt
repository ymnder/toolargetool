package com.gu.toolargetool

data class LoggerParam(
        val activityCallback: ActivityCallback,
        val fragmentCallback: FragmentCallback?
) {
    class Builder {
        private var activityCallback: ActivityCallback

        private var fragmentCallback: FragmentCallback?

        /**
         * Set activityCallback to call on [android.os.TransactionTooLargeException]
         */
        fun activityCallback(callback: ActivityCallback) = apply {
            this.activityCallback = callback
        }

        /**
         * Set fragmentCallback to call on [android.os.TransactionTooLargeException]
         */
        fun fragmentCallback(callback: FragmentCallback?) = apply {
            this.fragmentCallback = callback
        }

        fun build(): LoggerParam = LoggerParam(
                activityCallback = activityCallback,
                fragmentCallback = fragmentCallback
        )

        init {
            activityCallback = ActivityCallback { activity, bundle ->
                //NOP
            }
            fragmentCallback = FragmentCallback { fm, f, bundle ->
                //NOP
            }
        }

        companion object {
            @JvmStatic
            fun build(f: Builder.() -> Unit): LoggerParam {
                val builder = Builder()
                builder.f()
                return builder.build()
            }

            @JvmStatic
            fun newBuilder(f: Builder.() -> Unit): LoggerParam.Builder {
                val builder = Builder()
                builder.f()
                return builder
            }
        }
    }
}
