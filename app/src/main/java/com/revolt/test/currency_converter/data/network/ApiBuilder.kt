package com.myradios.data

import com.revolt.test.currency_converter.data.network.OkHttpBuilder
import okhttp3.Interceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit


class ApiBuilder(val okHttpBuilder: OkHttpBuilder,
                 val callAdapterFactory: CallAdapter.Factory,
                 val converterFactory: Converter.Factory) {

    companion object {
        const val baseUrl = "https://revolut.duckdns.org"
    }

    inline fun <reified T> build(vararg interceptor: Interceptor): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .client(okHttpBuilder.build(interceptor))
            .build()
            .create(T::class.java)
    }

}