package com.example.ascendcommerce.data.net

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonUtils {

    fun build(): Gson {
        return GsonBuilder().create()
    }
}