package com.gu.toolargetool

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class UtilsKtTest {

    @Test
    fun kb() {
        val kb = KB.kb()
        assertThat(kb).isEqualTo(10.0f)
    }

    companion object {
        private const val KB = 10000
    }
}