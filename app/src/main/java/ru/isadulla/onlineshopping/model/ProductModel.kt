package ru.isadulla.onlineshopping.model

import java.io.Serializable

data class ProductModel(
    val id: Int,
    val name: String,
    val price: String,
    val image: String,
    var cart_count: Int,
    val category_id: Int,
    val create_at: String,
    val update_at: String,
) : Serializable
