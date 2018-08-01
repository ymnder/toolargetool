package com.gu.toolargetool

import android.os.Bundle
import androidx.test.runner.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UtilsKtTest {


    @Test
    fun sizeAsParcel() {
        val bundle = Bundle()
        bundle.putString("TEST", "t")
        val parcel = bundle.sizeAsParcel()
        assertThat(parcel).isEqualTo(25)
    }

    @Test
    fun kb() {
        val kb = KB.kb()
        assertThat(kb).isEqualTo(10.0f)
    }

    companion object {
        private const val KB = 10000
    }
}