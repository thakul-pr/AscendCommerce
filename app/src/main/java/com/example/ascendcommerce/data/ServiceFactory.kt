package com.example.ascendcommerce.data

import com.example.ascendcommerce.data.net.RetrofitUtils

object ServiceFactory {
    fun <API> create(service: Class<API>): API {
        return RetrofitUtils.setupRetrofit().create(service)
    }
}