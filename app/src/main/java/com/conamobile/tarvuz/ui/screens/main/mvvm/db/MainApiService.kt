package com.conamobile.tarvuz.ui.screens.main.mvvm.db

import com.conamobile.tarvuz.BuildConfig
import com.conamobile.tarvuz.ui.screens.main.mvvm.model.PostsDummy
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApiService {

    @GET("user")
    suspend fun loadPosts(@Query("page") page: Int, @Query("limit") limit: Int): PostsDummy


    companion object {

        private const val BASE_URL = "https://dummyapi.io/data/v1/"

        operator fun invoke(): MainApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getRetrofitClient())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(MainApiService::class.java)
        }

        private fun getRetrofitClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    chain.proceed(chain.request().newBuilder().also {
                        it.addHeader("Accept", "application/json")
                        it.addHeader("app-id", "62cceaafb592b449c3aad899")
                    }.build())
                }.also { client ->
                    if (BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor()
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }
                }.build()
        }
    }

}