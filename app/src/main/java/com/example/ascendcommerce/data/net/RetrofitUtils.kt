package com.example.ascendcommerce.data.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUtils {

    internal fun setupRetrofit(): Retrofit {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)

        val okHttpClient = okHttpClientBuilder.build()

        val gson = GsonUtils.build()

        return Retrofit.Builder()
            .baseUrl("https://ecommerce-product-app.herokuapp.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}