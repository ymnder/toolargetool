package com.gu.toolargetool

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.test.runner.AndroidJUnit4
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UtilsKtTest {
    private val key = "TEST"
    private val value = "t"

    private val bundle = Bundle().apply { putString(key, value) }

    @Test
    fun format_activity() {
        val activity = mock<Activity>()
        val formatted = format(activity, bundle)

        val bundleHashCode = System.identityHashCode(bundle)
        assertThat(formatted).isEqualTo("${activity.javaClass.simpleName}.onSaveInstanceState wrote: Bundle@$bundleHashCode contains 1 keys and measures 0.0 KB when serialized as a Parcel\n* TEST = 0.0 KB")
    }

    @Test
    fun format_fragment() {
        val fm = mock<FragmentManager>()
        val fragment = spy<Fragment>()
        val argument = Bundle().apply { putString(key, value + value) }
        fragment.arguments = argument
        val formatted = format(fm, fragment, bundle)
        val bundleHashCode = System.identityHashCode(bundle)
        val argumentHashCode = System.identityHashCode(argument)
        assertThat(formatted).isEqualTo(
                "${fragment.javaClass.simpleName}.onSaveInstanceState wrote: Bundle@$bundleHashCode contains 1 keys and measures 0.0 KB when serialized as a Parcel" +
                        "\n* TEST = 0.0 KB" +
                        "\n* fragment arguments = Bundle@$argumentHashCode contains 1 keys and measures 0.0 KB when serialized as a Parcel" +
                        "\n* TEST = 0.0 KB"
        )
    }

    @Test
    fun bundleBreakdown() {
        bundle.apply {
            putString(key, value.repeat(10000))
        }
        val result = bundle.bundleBreakdown()
        val hashCode = System.identityHashCode(bundle)
        assertThat(result).isEqualTo("Bundle@$hashCode contains 1 keys and measures 10.0 KB when serialized as a Parcel\n* TEST = 10.0 KB")
    }

    @Test
    fun valueSizes() {
        val valueSizes = bundle.valueSizes()
        assertThat(valueSizes["TEST"]).isEqualTo(13)
    }


    @Test
    fun sizeAsParcel() {
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