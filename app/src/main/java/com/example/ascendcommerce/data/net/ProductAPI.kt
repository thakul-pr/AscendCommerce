package com.example.ascendcommerce.data.net

import com.example.ascendcommerce.data.model.Product
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductAPI {

    @POST("products")
    fun getProducts(): Call<ArrayList<Product>>

    @POST("products/{productId}")
    fun getProductDetail(@Path("productId") productId: Long): Call<Product>
}