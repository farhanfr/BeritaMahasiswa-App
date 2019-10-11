package com.example.beritamahasiswa.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServer {
    private const val BASE_URL = "http://192.168.1.11:8000/beritamahasiswa/"
    private val okHttp = OkHttpClient.Builder()
    private val builder = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())
    private val retrofit = builder.build()

    fun <T> buildService(serviceType:Class<T>): T{
        return retrofit.create(serviceType)
    }


}