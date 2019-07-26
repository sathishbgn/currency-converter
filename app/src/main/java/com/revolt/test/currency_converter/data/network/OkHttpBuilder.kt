package com.revolt.test.currency_converter.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class OkHttpBuilder {
    companion object {
        const val HTTP_TIMEOUT = 60L
    }

    fun build(interceptors: Array<out Interceptor>): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS)
            .apply {
                interceptors.forEach {
                    addInterceptor(it)
                }
            }
            .build()
    }
}