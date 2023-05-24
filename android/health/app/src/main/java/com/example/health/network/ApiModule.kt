package com.example.health.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiModule {
    private fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(300000L, TimeUnit.MILLISECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl("http://3.34.97.3") // will set in BaseInterceptor
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideApiService(): ApiService {
        val okHttpClient = provideOkHttpClient()
        val retrofit = provideRetrofit(okHttpClient)
        return retrofit.create(ApiService::class.java)
    }
}