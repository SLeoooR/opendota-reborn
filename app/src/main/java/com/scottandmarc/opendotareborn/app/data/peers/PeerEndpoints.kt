package com.scottandmarc.opendotareborn.app.data.peers

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PeerEndpoints {
    @GET("players/{account_id}/peers")
    suspend fun fetchPeers(
        @Path("account_id") accountId: Int
    ): Response<List<RemotePeer>>
}

fun createPeerService(): PeerEndpoints {
    val gson = GsonBuilder()
        .registerTypeAdapter(object : TypeToken<Array<RemotePeer>>() {}.type, PeerDeserializer())
        .setLenient()
        .create()

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.opendota.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    return retrofit.create(PeerEndpoints::class.java)
}