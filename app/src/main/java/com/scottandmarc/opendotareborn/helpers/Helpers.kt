package com.scottandmarc.opendotareborn.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.cert.X509Certificate
import java.text.SimpleDateFormat
import java.util.*
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class Helpers {
    companion object {
        fun getUnsafeOkHttpClient(): OkHttpClient {

            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(@SuppressLint("CustomX509TrustManager")
            object : X509TrustManager {
                @SuppressLint("TrustAllX509TrustManager")
                override fun checkClientTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                override fun checkServerTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers() = arrayOf<X509Certificate>()
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

//            val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
//                //Log message
//                Log.e("Interceptor", message)
//            })

            //interceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                //.addInterceptor(SupportInterceptor(context))
                .addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                .hostnameVerifier { _, _ -> true }.build()
        }

        fun createCustomPicassoLoader(context: Context): Picasso {
            val picassoBuilder: Picasso.Builder = Picasso.Builder(context)
            val downloader = getUnsafeOkHttpClient()
            picassoBuilder.downloader(OkHttp3Downloader(downloader))
            return picassoBuilder.build()
        }

        fun getDateTimeNow(): String {
            val date = Date()
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

            return formatter.format(date)
        }
    }
}
