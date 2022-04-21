package com.scottandmarc.opendotareborn.app.data.search

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.scottandmarc.opendotareborn.app.data.teams.RemoteTeam
import com.scottandmarc.opendotareborn.app.data.teams.TeamDeserializer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchEndpoints {
    @GET("search?")
    suspend fun fetchSearches(
        @Query("q") query: String
    ): Response<List<RemoteSearch>>
}

fun createSearchService(): SearchEndpoints {
    val gson = GsonBuilder()
        .registerTypeAdapter(object : TypeToken<Array<RemoteSearch>>() {}.type, TeamDeserializer())
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

    return retrofit.create(SearchEndpoints::class.java)
}