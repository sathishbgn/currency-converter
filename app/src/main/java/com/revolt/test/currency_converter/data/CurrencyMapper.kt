package com.revolt.test.currency_converter.data

import com.revolt.test.currency_converter.data.entity.CurrencyEntity
import com.revolt.test.currency_converter.domain.model.Currencies
import com.revolt.test.currency_converter.domain.model.Currency
import java.lang.NullPointerException

class CurrencyMapper {

    fun transformCurrency(currencyEntity: CurrencyEntity?): Currencies {
        val currencies = currencyEntity?.let {
            if (currencyEntity?.base == null) {
                throw NullPointerException()
            }
            Currencies(
                it.base!!,
                it.date!!,
                transformCurrencyList(it.rates)
            )
        } ?: run {
            Currencies("", "", listOf())
        }
        return currencies
    }


    private fun transformCurrencyList(currencyMap: HashMap<String, Double>?): List<Currency> {
        val currencyList = currencyMap?.let {
            mutableListOf<Currency>().also {
                for (key in currencyMap.keys) {
                    it.add(Currency(key, currencyMap[key]!!))
                }
            }
        } ?: run {
            listOf<Currency>()
            throw NullPointerException()
        }
        return currencyList
    }
}