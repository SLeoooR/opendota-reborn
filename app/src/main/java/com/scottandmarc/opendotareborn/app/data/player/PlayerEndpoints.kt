package com.scottandmarc.opendotareborn.app.data.player

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayerEndpoints {
    @GET("players/{account_id}")
    suspend fun fetchPlayer(
        @Path("account_id") accountId: Int
    ): Response<RemotePlayer>

    @GET("players/{account_id}/wl")
    suspend fun fetchWinLose(
        @Path("account_id") accountId: Int
    ): Response<RemoteWinLose>
}

fun createPlayerService(): PlayerEndpoints {
    val gson = GsonBuilder()
        .registerTypeAdapter(object : TypeToken<RemotePlayer>() {}.type, PlayerDeserializer())
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

    return retrofit.create(PlayerEndpoints::class.java)
}