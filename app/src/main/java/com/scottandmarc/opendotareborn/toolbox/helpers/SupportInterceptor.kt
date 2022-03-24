package com.scottandmarc.opendotareborn.toolbox.helpers

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class SupportInterceptor(context: Context) : Interceptor {

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "")
            .build()

        return chain.proceed(request)
    }
}
