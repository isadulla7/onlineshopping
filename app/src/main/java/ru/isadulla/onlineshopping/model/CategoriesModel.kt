package ru.isadulla.onlineshopping.model

data class CategoriesModel(
    val id: Int,
    val title: String,
    val icon: String,
    val image: String,
    val parent_id: Int,
    var checked: Boolean = false,
    val create_at: String,
    val update_at: String,
)
