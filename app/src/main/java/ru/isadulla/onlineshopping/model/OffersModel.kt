package ru.isadulla.onlineshopping.model


data class OffersModel(
    val id: Int,
    val title: String,
    val icon: String,
    val image: String,
    val parent_id: Int,
    val create_at: String,
    val update_at: String,
)