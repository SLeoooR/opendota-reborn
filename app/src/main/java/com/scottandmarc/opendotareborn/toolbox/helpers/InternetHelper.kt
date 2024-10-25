package com.scottandmarc.opendotareborn.toolbox.helpers

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.os.Build

object NetworkUtils {
    @Suppress("DEPRECATION")
    fun Context?.isNetworkAvailable(): Boolean {
        return if (this == null) false
        else {
            val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                capabilities.isCapabilityValid()
            } else {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                activeNetworkInfo != null && activeNetworkInfo.isConnected
            }
        }
    }

    private fun NetworkCapabilities?.isCapabilityValid(): Boolean {
        return if (this != null) {
            when {
                hasTransport(TRANSPORT_CELLULAR) -> true
                hasTransport(TRANSPORT_WIFI) -> true
                hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            false
        }
    }
}