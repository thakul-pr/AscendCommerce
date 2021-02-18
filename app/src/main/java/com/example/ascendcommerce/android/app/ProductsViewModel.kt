package com.example.ascendcommerce.android.app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ascendcommerce.android.app.lifecycle.ResourceLiveData
import com.example.ascendcommerce.data.model.Product
import com.example.ascendcommerce.data.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsViewModel(application: Application) : AndroidViewModel(application) {
    val liveData = ResourceLiveData<List<Product>>()

    fun getProducts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                ProductsRepository.getProducts(liveData)
            }
        }
    }
}