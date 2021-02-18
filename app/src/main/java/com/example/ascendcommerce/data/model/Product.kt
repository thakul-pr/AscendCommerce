package com.example.ascendcommerce.data.model

data class Product(
    var id: Long? = null,
    var title: String? = null,
    var image: String? = null,
    var content: String? = null,
    var isNewProduct: Boolean = false,
    var price: Double? = null
) {
    fun isValidProduct(): Boolean {
        return id != null && title != null
    }
}