package com.example.ascendcommerce.android.app

import android.os.Bundle
import com.example.ascendcommerce.R

class ProductsActivity : ToolbarActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        setTitle(R.string.products)
    }
}