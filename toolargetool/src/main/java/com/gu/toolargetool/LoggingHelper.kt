package com.gu.toolargetool

import android.os.Bundle
import android.os.Parcel
import java.util.*

class LoggingHelper {

    /**
     * Return a formatted String containing a breakdown of the contents of a [Bundle].
     *
     * @param bundle to format
     * @return a nicely formatted string (multi-line)
     */
    fun bundleBreakdown(bundle: Bundle): String {
        var result = String.format(
                Locale.UK,
                "Bundle@%d contains %d keys and measures %,.1f KB when serialized as a Parcel",
                System.identityHashCode(bundle), bundle.size(), KB(sizeAsParcel(bundle))
        )
        for ((key, value) in valueSizes(bundle)) {
            result += String.format(
                    Locale.UK,
                    "\n* %s = %,.1f KB",
                    key, KB(value)
            )
        }
        return result
    }

    /**
     * Measure the sizes of all the values in a typed [Bundle] when written to a
     * [Parcel]. Returns a map from keys to the sizes, in bytes, of the associated values in
     * the Bundle.
     *
     * @param bundle to measure
     * @return a map from keys to value sizes in bytes
     */
    fun valueSizes(bundle: Bundle): Map<String, Int> {
        val result = HashMap<String, Int>(bundle.size())
        // We measure the size of each value by measuring the total size of the bundle before and
        // after removing that value and calculating the difference. We make a copy of the original
        // bundle so we can put all the original values back at the end. It's not possible to
        // carry out the measurements on the copy because of the way Android parcelables work
        // under the hood where certain objects are actually stored as references.
        val copy = Bundle(bundle)
        try {
            var bundleSize = sizeAsParcel(bundle)
            // Iterate over copy's keys because we're removing those of the original bundle
            for (key in copy.keySet()) {
                bundle.remove(key)
                val newBundleSize = sizeAsParcel(bundle)
                val valueSize = bundleSize - newBundleSize
                result[key] = valueSize
                bundleSize = newBundleSize
            }
            return result
        } finally {
            // Put everything back into original bundle
            bundle.putAll(copy)
        }
    }

    /**
     * Measure the size of a typed [Bundle] when written to a [Parcel].
     *
     * @param bundle to measure
     * @return size when written to parcel in bytes
     */
    fun sizeAsParcel(bundle: Bundle): Int {
        val parcel = Parcel.obtain()
        try {
            parcel.writeBundle(bundle)
            return parcel.dataSize()
        } finally {
            parcel.recycle()
        }
    }

    private fun KB(bytes: Int): Float {
        return bytes.toFloat() / 1000f
    }
}