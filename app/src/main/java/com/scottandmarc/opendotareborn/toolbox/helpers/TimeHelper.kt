package com.scottandmarc.opendotareborn.toolbox.helpers

import java.lang.Exception
import java.sql.Timestamp
import java.util.*

object TimeHelper {
    fun numTimeAgo(lastDate: Int): String {
        val currentDate = Timestamp(System.currentTimeMillis()).time
        val lastPlayedDate = Date(lastDate.toLong() * 1000).time

        val difference: Long = kotlin.math.abs(currentDate - lastPlayedDate)
        val minutes = (difference / (60 * 1000))
        val hours = minutes / 60

        val hoursInDay = 24
        val hoursInWeek = 168
        val hoursInMonth = 730
        val hoursInYear = 8760
        when {
            minutes < 60 -> {
                // Return Minutes
                return "$minutes mins"
            }
            hours < hoursInDay -> {
                // Return Hours
                return "$hours hours"
            }
            hours in hoursInDay until hoursInWeek -> {
                // Return Days
                return "${hours / hoursInDay} days"
            }
            hours in hoursInWeek until hoursInMonth -> {
                // Return Weeks
                return "${hours / hoursInWeek} weeks"
            }
            hours in hoursInMonth until hoursInYear -> {
                // Return Months
                return "${hours / hoursInMonth} months"
            }
            hours < hoursInYear || hours > hoursInYear -> {
                // Return Years
                return "${hours / hoursInYear} years"
            }
            else -> {
                throw Exception("Unknown Format")
            }
        }
    }
}