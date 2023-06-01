package com.example.health.network

import com.example.health.local.PrefDataStore
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object ApiModule {
    private fun provideOkHttpClient(prefDataStore: PrefDataStore): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(300000L, TimeUnit.MILLISECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(AppInterceptor(prefDataStore))
            .build()
    }

    private fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl("http://3.34.97.3") // will set in BaseInterceptor
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideApiService(prefDataStore: PrefDataStore): ApiService {
        val okHttpClient = provideOkHttpClient(prefDataStore)
        val retrofit = provideRetrofit(okHttpClient)
        return retrofit.create(ApiService::class.java)
    }

}

class AppInterceptor(private val prefDataStore: PrefDataStore) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {

        val accessToken = runBlocking { prefDataStore.getAccessToken() }
        val builder = chain.request().newBuilder()

        if (!accessToken.isNullOrEmpty()) {
            builder.addHeader("Authorization", "BEARER $accessToken")
        }

        chain.proceed(builder.build())
    }
}