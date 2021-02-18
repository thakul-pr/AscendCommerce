package com.example.ascendcommerce.android.app

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.ascendcommerce.R
import com.example.ascendcommerce.android.app.lifecycle.ServiceCallObserver
import com.example.ascendcommerce.data.model.Product
import com.example.ascendcommerce.image.GlideUtils

class ProductsActivity : ToolbarActivity() {

    private var productsAdapter: ProductsAdapter? = null
    private var viewModel: ProductsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        setTitle(R.string.products)

        viewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
        productsAdapter = ProductsAdapter()

        findViewById<RecyclerView>(R.id.recyclerView)?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
            layoutManager = GridLayoutManager(this@ProductsActivity, 2)
            addItemDecoration(ProductItemDecoration(this@ProductsActivity))
            adapter = productsAdapter
        }

        findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)?.apply {
            setOnRefreshListener {
                viewModel?.getProducts()
            }
        }

        viewModel?.getProducts()
        viewModel?.liveData?.observe(this, object : ServiceCallObserver<List<Product>>() {
            override fun postIsLoading(value: Boolean) {
            }

            override fun postValue(value: List<Product>?) {
                value?.also {
                    productsAdapter?.setData(value)
                }
            }

            override fun postError(exception: Exception?) {
                TODO("Not yet implemented")
            }

        })
    }

    inner class ProductItemDecoration(context: Context) :
        RecyclerView.ItemDecoration() {

        private val padding =
            context.resources.getDimensionPixelSize(R.dimen.space_half)

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)

            outRect.bottom = padding
            outRect.top = padding
            outRect.right = padding
            outRect.left = padding
        }
    }

    /////////// ADAPTER ////////////////

    private class ProductsAdapter : RecyclerView.Adapter<ProductsHolder>() {
        private var data = listOf<Product>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsHolder {
            return ProductsHolder(
                parent.context,
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_item_products,
                    parent,
                    false
                )
            )
        }

        override fun getItemCount(): Int = data.size

        override fun onBindViewHolder(holder: ProductsHolder, position: Int) {
            val item = data[position]
            holder.bindView(item)
        }

        fun setData(data: List<Product>) {
            val validProducts = data.filter {
                it.isValidProduct()
            }
            this.data = validProducts
            notifyDataSetChanged()
        }
    }

    private class ProductsHolder(val context: Context, itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val newProduct: View = itemView.findViewById(R.id.newProduct)
        val productName: TextView = itemView.findViewById(R.id.productName)
        val price: TextView = itemView.findViewById(R.id.price)

        fun bindView(product: Product) {
            product.image?.also {
                GlideUtils.loadImage(
                    context,
                    it,
                    productImage,
                    R.drawable.ic_product_place_holder_24dp
                )
            } ?: run {
                productImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_product_place_holder_24dp
                    )
                )
            }

            if (product.isNewProduct) {
                newProduct.visibility = View.VISIBLE
            } else {
                newProduct.visibility = View.GONE
            }

            productName.text = product.title
            price.text = product.price?.toString()
        }
    }

}