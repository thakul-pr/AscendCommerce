package com.example.ascendcommerce.android.app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.ascendcommerce.R
import com.example.ascendcommerce.data.model.Product
import com.example.ascendcommerce.databinding.ActivityDetailBinding
import com.example.ascendcommerce.image.GlideUtils

class DetailActivity : ToolbarActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setTitle(R.string.detail)

        findViewById<View>(R.id.back)?.apply {
            visibility = View.VISIBLE
            setOnClickListener {
                finish()
            }
        }

        intent?.getParcelableExtra<Product>(EXTRA_PRODUCT)?.also { product ->
            product.image?.also {
                GlideUtils.loadImage(this, it, binding.productImage)
            }

            if (product.isNewProduct) {
                binding.newProduct.visibility = View.VISIBLE
            }

            binding.productName.text = product.title
            binding.price.text = product.price?.toString()
            binding.productContent.text = product.content
            binding.productContent.visibility = View.VISIBLE
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