package com.gfq.summery.net

import com.gfq.common.utils.LogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


var token = ""

private val okClient = OkHttpClient.Builder()
    .addInterceptor {
        it.proceed(it.request().newBuilder().addHeader("token", token).build())
    }
    .addInterceptor(LogInterceptor())
    .connectTimeout(30, TimeUnit.SECONDS)
    .callTimeout(120, TimeUnit.SECONDS)
    .pingInterval(30, TimeUnit.SECONDS)
    .readTimeout(60, TimeUnit.SECONDS)
    .writeTimeout(60, TimeUnit.SECONDS)
    .build()


private val retrofit: Retrofit = Retrofit.Builder()
    .client(okClient)
    .baseUrl("http://192.168.110.81:8080/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()


val apiService: ServiceApi by lazy { retrofit.create(ServiceApi::class.java) }

