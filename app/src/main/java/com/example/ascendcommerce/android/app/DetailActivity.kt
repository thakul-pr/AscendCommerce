package com.example.ascendcommerce.android.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.ascendcommerce.R
import com.example.ascendcommerce.data.model.Product
import com.example.ascendcommerce.image.GlideUtils

class DetailActivity : ToolbarActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setTitle(R.string.detail)

        findViewById<View>(R.id.back)?.apply {
            visibility = View.VISIBLE
            setOnClickListener {
                finish()
            }
        }

        intent?.getParcelableExtra<Product>(EXTRA_PRODUCT)?.also { product ->
            product.image?.also {
                findViewById<ImageView>(R.id.productImage)?.also { productImage ->
                    GlideUtils.loadImage(this, it, productImage)
                }
            }

            if (product.isNewProduct) {
                findViewById<View>(R.id.newProduct)?.visibility = View.VISIBLE
            }

            findViewById<TextView>(R.id.productName)?.text = product.title
            findViewById<TextView>(R.id.price)?.text = product.price?.toString()
            findViewById<TextView>(R.id.productContent)?.apply {
                text = product.content
                visibility = View.VISIBLE
            }
        }
    }

    companion object {
        private const val EXTRA_PRODUCT = "product"

        fun getIntent(context: Context, product: Product): Intent =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_PRODUCT, product)
            }
    }
}