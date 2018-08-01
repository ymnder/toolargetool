package com.gu.toolargetool

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class LoggerParamTest {

    @Test
    fun setActivityCallback() {
        val callback = ActivityCallback { _, _ ->  }
        val param = LoggerParam.Builder().activityCallback(callback).build()
        assertThat(param.activityCallback).isEqualTo(callback)
    }

    @Test
    fun setFragmentCallback() {
        val callback = FragmentCallback { _, _, _ ->  }
        val param = LoggerParam.Builder().fragmentCallback(callback).build()
        assertThat(param.fragmentCallback).isEqualTo(callback)
    }

    @Test
    fun setFragmentCallbackNull() {
        val param = LoggerParam.Builder().fragmentCallback(null).build()
        assertThat(param.fragmentCallback).isNull()
    }
}