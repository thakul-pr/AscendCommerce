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
import com.example.ascendcommerce.R
import com.example.ascendcommerce.android.app.lifecycle.ServiceCallObserver
import com.example.ascendcommerce.data.model.Product
import com.example.ascendcommerce.databinding.ActivityProductsBinding
import com.example.ascendcommerce.image.GlideUtils
import com.google.android.material.snackbar.Snackbar


class ProductsActivity : ToolbarActivity() {

    private var productsAdapter: ProductsAdapter? = null
    private var viewModel: ProductsViewModel? = null

    private lateinit var binding: ActivityProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setTitle(R.string.products)

        viewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
        productsAdapter = ProductsAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        binding.recyclerView.layoutManager = GridLayoutManager(this@ProductsActivity, 2)
        binding.recyclerView.addItemDecoration(ProductItemDecoration(this@ProductsActivity))
        binding.recyclerView.adapter = productsAdapter

        binding.swipeRefresh.setOnRefreshListener { viewModel?.getProducts() }

        setContentShown(false)

        viewModel?.liveData?.observe(this, object : ServiceCallObserver<List<Product>>() {
            override fun postIsLoading(value: Boolean) {
                if (value) {
                    if ((productsAdapter?.itemCount ?: 0) == 0) {
                        setContentShown(false)
                    } else {
                        binding.swipeRefresh.isRefreshing = true
                    }
                } else {
                    setContentShown(true)
                    binding.swipeRefresh.isRefreshing = false
                }
            }

            override fun postValue(value: List<Product>?) {
                value?.also {
                    productsAdapter?.setData(value)
                }
            }

            override fun postError(errorMessage: String?) {
                errorMessage?.also {
                    Snackbar.make(binding.swipeRefresh, errorMessage, Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel?.getProducts()
    }

    private fun setContentShown(shown: Boolean) {
        binding.recyclerView.visibility = if (shown) View.VISIBLE else View.GONE
        binding.legend.visibility = if (shown) if ((productsAdapter?.itemCount
                ?: 0) == 0
        ) View.GONE else View.VISIBLE else View.GONE
        binding.progressContainer.visibility = if (shown) View.GONE else View.VISIBLE
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
            itemView.setOnClickListener {
                it.context.startActivity(
                    DetailActivity.getIntent(
                        it.context,
                        product
                    )
                )
            }

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