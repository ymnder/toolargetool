package com.gu.toolargetool

import android.app.Activity
import android.os.Bundle
import android.os.Parcel
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import java.util.*

fun format(activity: Activity, bundle: Bundle) =
        ""

fun format(fm: FragmentManager, fragment: Fragment, bundle: Bundle): String {
    return ""
}

fun Bundle.bundleBreakdown(): String {
    return ""
}

fun Bundle.valueSizes(): Map<String, Int> {
    return HashMap<String, Int>()
}

/**
 * Measure the size of a typed [Bundle] when written to a [Parcel].
 *
 * @return size when written to parcel in bytes
 */
fun Bundle.sizeAsParcel(): Int {
    return 0
}

fun Int.kb(): Float = 1f