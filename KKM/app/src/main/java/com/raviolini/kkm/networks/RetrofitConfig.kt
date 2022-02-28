package com.raviolini.kkm.networks

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfig {
    private const val baseKostUrl = "http://103.176.79.55:8080"

    private val client by lazy{
        OkHttpClient.Builder()
            .addInterceptor{chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    private val retrofitBuilder : Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(baseKostUrl)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
    }

    val CLIENT_API : ClientAPI by lazy {
        retrofitBuilder.build().create(ClientAPI::class.java)
    }
}