package com.example.ascendcommerce

import android.os.Bundle

class DetailActivity : ToolbarActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setTitle(R.string.detail)
    }
}