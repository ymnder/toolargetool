package com.gu.toolargetool

import android.app.Activity
import android.os.Bundle
import android.os.Parcel
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.util.*

fun format(activity: Activity, bundle: Bundle) =
        "${activity.javaClass.simpleName}.onSaveInstanceState wrote: ${bundle.bundleBreakdown()}"

fun format(fm: FragmentManager, fragment: Fragment, bundle: Bundle): String {
    var message = "${fragment.javaClass.simpleName}.onSaveInstanceState wrote: ${bundle.bundleBreakdown()}"
    fragment.arguments?.let {
        message += "\n* fragment arguments = ${it.bundleBreakdown()}"
    }
    return message
}

/**
 * Return a formatted String containing a breakdown of the contents of a [Bundle].
 *
 * @param bundle to format
 * @return a nicely formatted string (multi-line)
 */
fun Bundle.bundleBreakdown(): String {
    var result = String.format(
            Locale.UK,
            "Bundle@%d contains %d keys and measures %,.1f KB when serialized as a Parcel",
            System.identityHashCode(this),
            this.size(),
            this.sizeAsParcel().kb()
    )
    for ((key, value) in this.valueSizes()) {
        result += String.format(
                Locale.UK,
                "\n* %s = %,.1f KB",
                key,
                value.kb()
        )
    }
    return result
}

/**
 * Measure the sizes of all the values in a typed [Bundle] when written to a
 * [Parcel]. Returns a map from keys to the sizes, in bytes, of the associated values in
 * the Bundle.
 *
 * @return a map from keys to value sizes in bytes
 */
fun Bundle.valueSizes(): Map<String, Int> {
    val result = HashMap<String, Int>(this.size())
    // We measure the size of each value by measuring the total size of the bundle before and
    // after removing that value and calculating the difference. We make a copy of the original
    // bundle so we can put all the original values back at the end. It's not possible to
    // carry out the measurements on the copy because of the way Android parcelables work
    // under the hood where certain objects are actually stored as references.
    val copy = Bundle(this)
    try {
        var bundleSize = this.sizeAsParcel()
        // Iterate over copy's keys because we're removing those of the original bundle
        for (key in copy.keySet()) {
            this.remove(key)
            val newBundleSize = this.sizeAsParcel()
            val valueSize = bundleSize - newBundleSize
            result[key] = valueSize
            bundleSize = newBundleSize
        }
        return result
    } finally {
        // Put everything back into original bundle
        this.putAll(copy)
    }
}

/**
 * Measure the size of a typed [Bundle] when written to a [Parcel].
 *
 * @return size when written to parcel in bytes
 */
fun Bundle.sizeAsParcel(): Int {
    val parcel = Parcel.obtain()
    try {
        parcel.writeBundle(this)
        return parcel.dataSize()
    } finally {
        parcel.recycle()
    }
}

fun Int.kb(): Float = this.toFloat() / 1000f