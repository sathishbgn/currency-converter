package com.revolt.test.currency_converter.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.myradios.data.ApiBuilder
import com.revolt.test.currency_converter.data.network.CurrencyService
import com.revolt.test.currency_converter.data.network.OkHttpBuilder
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory


val dataModule = module {

    single { OkHttpBuilder() }

    single { CoroutineCallAdapterFactory() }

    single<Converter.Factory> { GsonConverterFactory.create() }

    single { ApiBuilder(get(), get(), get()) }

    single<CurrencyService> { get<ApiBuilder>().build() }
}
