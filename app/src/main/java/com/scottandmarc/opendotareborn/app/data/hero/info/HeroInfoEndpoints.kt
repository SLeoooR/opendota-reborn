package com.scottandmarc.opendotareborn.app.data.hero.info

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface HeroInfoEndpoints {
    @GET("heroes")
    suspend fun fetchHeroesInfo(): Response<List<RemoteHeroInfo>>
}

fun createHeroInfoService(): HeroInfoEndpoints {
    val gson = GsonBuilder()
        .registerTypeAdapter(object : TypeToken<Array<RemoteHeroInfo>>() {}.type, HeroInfoDeserializer())
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

    return retrofit.create(HeroInfoEndpoints::class.java)
}