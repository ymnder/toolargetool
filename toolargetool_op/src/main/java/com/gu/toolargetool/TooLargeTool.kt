package com.gu.toolargetool

import android.app.Application

class TooLargeTool {
    @JvmOverloads
    fun startLogging(
            application: Application,
            param: LoggerParam = LoggerParam.Builder().build()
    ) {
        //NOP
    }

}