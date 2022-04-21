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
                return if (minutes == 1L) {
                    "$minutes min"
                } else {
                    "$minutes mins"
                }
            }
            hours < hoursInDay -> {
                // Return Hours
                return if (hours == 1L) {
                    "$hours hour"
                } else {
                    "$hours hours"
                }
            }
            hours in hoursInDay until hoursInWeek -> {
                // Return Days
                return if (hours / hoursInDay == 1L) {
                    "${hours / hoursInDay} day"
                } else {
                    "${hours / hoursInDay} days"
                }
            }
            hours in hoursInWeek until hoursInMonth -> {
                // Return Weeks
                return if (hours / hoursInWeek == 1L) {
                    "${hours / hoursInWeek} week"
                } else {
                    "${hours / hoursInWeek} weeks"
                }
            }
            hours in hoursInMonth until hoursInYear -> {
                // Return Months
                return if (hours / hoursInMonth == 1L) {
                    "${hours / hoursInMonth} month"
                } else {
                    "${hours / hoursInMonth} months"
                }
            }
            hours < hoursInYear || hours > hoursInYear -> {
                // Return Years
                return if (hours / hoursInYear == 1L) {
                    "${hours / hoursInYear} year"
                } else {
                    "${hours / hoursInYear} years"
                }
            }
            else -> {
                throw Exception("Unknown Format")
            }
        }
    }

    fun numTimeAgoUsingDate(lastDate: Date): String {
        val currentDate = Timestamp(System.currentTimeMillis()).time

        val difference: Long = kotlin.math.abs(currentDate - lastDate.time)
        val minutes = (difference / (60 * 1000))
        val hours = minutes / 60

        val hoursInDay = 24
        val hoursInWeek = 168
        val hoursInMonth = 730
        val hoursInYear = 8760
        when {
            minutes < 60 -> {
                // Return Minutes
                return if (minutes == 1L) {
                    "$minutes min"
                } else {
                    "$minutes mins"
                }
            }
            hours < hoursInDay -> {
                // Return Hours
                return if (hours == 1L) {
                    "$hours hour"
                } else {
                    "$hours hours"
                }
            }
            hours in hoursInDay until hoursInWeek -> {
                // Return Days
                return if (hours / hoursInDay == 1L) {
                    "${hours / hoursInDay} day"
                } else {
                    "${hours / hoursInDay} days"
                }
            }
            hours in hoursInWeek until hoursInMonth -> {
                // Return Weeks
                return if (hours / hoursInWeek == 1L) {
                    "${hours / hoursInWeek} week"
                } else {
                    "${hours / hoursInWeek} weeks"
                }
            }
            hours in hoursInMonth until hoursInYear -> {
                // Return Months
                return if (hours / hoursInMonth == 1L) {
                    "${hours / hoursInMonth} month"
                } else {
                    "${hours / hoursInMonth} months"
                }
            }
            hours < hoursInYear || hours > hoursInYear -> {
                // Return Years
                return if (hours / hoursInYear == 1L) {
                    "${hours / hoursInYear} year"
                } else {
                    "${hours / hoursInYear} years"
                }
            }
            else -> {
                throw Exception("Unknown Format")
            }
        }
    }
}