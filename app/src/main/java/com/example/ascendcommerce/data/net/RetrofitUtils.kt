package com.example.ascendcommerce.data.net

import com.example.ascendcommerce.data.net.body.GsonUtils
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUtils {

    internal fun setupRetrofit(): Retrofit {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)

        val okHttpClient = okHttpClientBuilder.build()

        val gson = GsonUtils.build()

        // FIXME
        return Retrofit.Builder()
            .baseUrl("https://localhost")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}