package com.revolt.test.currency_converter.di

import com.revolt.test.currency_converter.domain.usecase.CurrencyCase
import org.koin.dsl.module

val domainModule = module {

    single { CurrencyCase() }
}