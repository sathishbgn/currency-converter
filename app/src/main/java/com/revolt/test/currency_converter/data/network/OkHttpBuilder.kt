package com.revolt.test.currency_converter.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class OkHttpBuilder {
    companion object {
        const val HTTP_TIMEOUT = 60L
    }

    fun build(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .connectTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor).build()
    }
}