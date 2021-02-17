package com.example.ascendcommerce.data.repository

import com.example.ascendcommerce.android.app.lifecycle.ResourceLiveData
import com.example.ascendcommerce.data.ServiceFactory
import com.example.ascendcommerce.data.model.Product
import com.example.ascendcommerce.data.net.ProductAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object ProductsRepository {
    private val productAPI = ServiceFactory.create(ProductAPI::class.java)

    fun getProducts(liveData: ResourceLiveData<ArrayList<Product>>) {
        productAPI.getProducts()
            .enqueue(object :
                Callback<ArrayList<Product>?> {

                override fun onResponse(
                    call: Call<ArrayList<Product>?>,
                    response: Response<ArrayList<Product>?>
                ) {
                    liveData.postResponse(response.body())
                }

                override fun onFailure(call: Call<ArrayList<Product>?>, t: Throwable) {
                    // todo
                }
            })
    }

}