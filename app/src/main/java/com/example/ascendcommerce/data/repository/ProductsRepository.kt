package com.example.ascendcommerce.data.repository

import android.content.Context
import androidx.annotation.WorkerThread
import com.example.ascendcommerce.R
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
    fun getProducts(context: Context, liveData: ResourceLiveData<List<Product>>) {
        productAPI.getProducts()
            .enqueue(object :
                Callback<List<Product>?> {

                override fun onResponse(
                    call: Call<List<Product>?>,
                    response: Response<List<Product>?>
                ) {
                    if (response.isSuccessful) {
                        liveData.postResponse(response.body())
                    } else {
                        liveData.postError(response.errorBody().toString())
                    }

                }

                override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
                    liveData.postError(context.getString(R.string.message_common_error))
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