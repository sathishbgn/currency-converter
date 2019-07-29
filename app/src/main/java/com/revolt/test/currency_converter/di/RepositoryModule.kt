package com.revolt.test.currency_converter.di

import com.revolt.test.currency_converter.data.repository.CurrencyRepository
import com.revolt.test.currency_converter.domain.contractor.CurrencyContractor
import org.koin.dsl.module

val repositoryModule = module {

    single<CurrencyContractor> { CurrencyRepository(get()) }
}