package com.example.ascendcommerce.data.repository

import androidx.annotation.WorkerThread
import com.example.ascendcommerce.android.app.lifecycle.ResourceLiveData
import com.example.ascendcommerce.data.ServiceFactory
import com.example.ascendcommerce.data.model.Product
import com.example.ascendcommerce.data.net.ProductAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object ProductsRepository {
    private val productAPI = ServiceFactory.create(ProductAPI::class.java)

    @WorkerThread
    fun getProducts(liveData: ResourceLiveData<List<Product>>) {
        productAPI.getProducts()
            .enqueue(object :
                Callback<List<Product>?> {

                override fun onResponse(
                    call: Call<List<Product>?>,
                    response: Response<List<Product>?>
                ) {
                    liveData.postResponse(response.body())
                }

                override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                    // todo
                }
            })
    }

    // I'm not sure if I should use this service or not
    // because I have received the data I want from products service.
    @WorkerThread
    fun getProduct(productId: Long, liveData: ResourceLiveData<Product>) {
        productAPI.getProductDetail(productId)
            .enqueue(object :
                Callback<Product> {

                override fun onResponse(
                    call: Call<Product>,
                    response: Response<Product>
                ) {
                    liveData.postResponse(response.body())
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    // todo
                }
            })
    }

}