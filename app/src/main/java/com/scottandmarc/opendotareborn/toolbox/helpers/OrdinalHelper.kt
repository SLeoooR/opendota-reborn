package com.scottandmarc.opendotareborn.toolbox.helpers

object OrdinalHelper {
    fun intToOrdinal(i: Int): String {
        val suffixes = arrayOf("th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th")
        return when (i % 100) {
            11, 12, 13 -> i.toString() + "th"
            else -> i.toString() + suffixes[i % 10]
        }
    }
}