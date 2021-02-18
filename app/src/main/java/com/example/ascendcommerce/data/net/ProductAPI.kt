package com.example.ascendcommerce.data.net

import com.example.ascendcommerce.data.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductAPI {

    @GET("products")
    fun getProducts(): Call<List<Product>>

    @GET("products/{productId}")
    fun getProductDetail(@Path("productId") productId: Long): Call<Product>
}