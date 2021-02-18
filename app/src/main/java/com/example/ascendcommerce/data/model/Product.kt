package com.example.ascendcommerce.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    var id: Long? = null,
    var title: String? = null,
    var image: String? = null,
    var content: String? = null,
    var isNewProduct: Boolean = false,
    var price: Double? = null
) : Parcelable {

    fun isValidProduct(): Boolean {
        return id != null && title != null
    }
}