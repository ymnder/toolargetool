package com.gu.toolargetool

data class LoggerParam(
        val activityCallback: TooLargeLoggerCallback,
        val fragmentCallback: TooLargeLoggerCallback?
) {
    class Builder {
        private var activityCallback: TooLargeLoggerCallback

        private var fragmentCallback: TooLargeLoggerCallback?

        /**
         * Set activityCallback to call on {@link android.os.TransactionTooLargeException}
         */
        fun activityCallback(callback: TooLargeLoggerCallback) = apply {
            this.activityCallback = callback
        }
        /**
         * Set fragmentCallback to call on {@link android.os.TransactionTooLargeException}
         */
        fun fragmentCallback(callback: TooLargeLoggerCallback?) = apply {
            this.fragmentCallback = callback
        }

        fun build(): LoggerParam = LoggerParam(
                activityCallback = activityCallback,
                fragmentCallback = fragmentCallback
        )

        init {
            activityCallback = TooLargeLoggerCallback {
                //TODO
            }
            fragmentCallback = TooLargeLoggerCallback {
                //TODO
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