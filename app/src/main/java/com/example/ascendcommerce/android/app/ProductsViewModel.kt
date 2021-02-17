package com.example.ascendcommerce.android.app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.ascendcommerce.android.app.lifecycle.ResourceLiveData
import com.example.ascendcommerce.data.model.Product
import com.example.ascendcommerce.data.repository.ProductsRepository

class ProductsViewModel(application: Application) : AndroidViewModel(application) {
    val liveData = ResourceLiveData<ArrayList<Product>>()

    fun getProducts() {
        ProductsRepository.getProducts(liveData)
    }
}