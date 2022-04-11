package com.scottandmarc.opendotareborn.toolbox.retrofit

import android.content.Context
import com.scottandmarc.opendotareborn.toolbox.helpers.NetworkUtils.isNetworkAvailable

class NetworkConnectionChecker(
    private val context: Context
) {
    fun isNetworkAvailable() = context.isNetworkAvailable()
}