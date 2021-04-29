package ru.isadulla.onlineshopping.model.request

data class GetProductsByIdsRequest(
    val products: List<Int>

)