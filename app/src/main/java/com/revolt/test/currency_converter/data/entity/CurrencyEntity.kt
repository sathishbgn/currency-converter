package com.revolt.test.currency_converter.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrencyEntity(
    @SerializedName("base")
    val base: String?,

    @SerializedName("date")
    val date: String?,

    @SerializedName("rates")
    @Expose
    val rates: HashMap<String, Int>?
)