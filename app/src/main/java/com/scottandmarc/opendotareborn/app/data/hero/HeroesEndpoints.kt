package com.scottandmarc.opendotareborn.app.data.hero

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroesEndpoints {
    @GET("players/{account_id}/heroes")
    suspend fun fetchHeroes(
        @Path("account_id") accountId: Int?
    ): Response<List<RemoteHeroes.RemoteHero>>
}

fun createHeroesService(): HeroesEndpoints {
    val gson = GsonBuilder()
        .registerTypeAdapter(object : TypeToken<RemoteHeroes>() {}.type, HeroesDeserializer())
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

    return retrofit.create(HeroesEndpoints::class.java)
}