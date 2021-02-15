package com.example.ascendcommerce

import android.os.Bundle

class ProductsActivity : ToolbarActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        setTitle(R.string.products)
    }
}