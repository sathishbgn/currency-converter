package com.myradios.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.revolt.test.currency_converter.data.network.OkHttpBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiBuilder(val okHttpBuilder: OkHttpBuilder) {

    companion object {
        const val baseUrl = "https://revolut.duckdns.org"
    }

    inline fun <reified T> build(): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpBuilder.build())
            .build()
            .create(T::class.java)
    }

}