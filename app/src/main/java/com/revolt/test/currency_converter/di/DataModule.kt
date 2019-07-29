package com.revolt.test.currency_converter.di

import com.myradios.data.ApiBuilder
import com.revolt.test.currency_converter.data.network.CurrencyApi
import com.revolt.test.currency_converter.data.network.OkHttpBuilder
import org.koin.dsl.module

val dataModule = module {

    single { OkHttpBuilder() }

    single { ApiBuilder(get()) }

    single<CurrencyApi> { get<ApiBuilder>().build() }
}

