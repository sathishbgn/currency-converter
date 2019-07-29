package com.revolt.test.currency_converter.di

import com.revolt.test.currency_converter.presentation.presenter.CurrencyPresenter
import org.koin.dsl.module

val presenterModule = module {

    single { CurrencyPresenter(get()) }
}