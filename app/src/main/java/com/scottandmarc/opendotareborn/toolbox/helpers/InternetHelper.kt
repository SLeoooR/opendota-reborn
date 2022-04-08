package com.scottandmarc.opendotareborn.toolbox.helpers

import java.net.InetAddress

object InternetHelper {
    fun isInternetAvailable(): Boolean {
        return try {
            val ipAddress: InetAddress = InetAddress.getByName("google.com")
            //You can replace it with your name
            !ipAddress.equals("")
        } catch (e: Exception) {
            false
        }
    }
}