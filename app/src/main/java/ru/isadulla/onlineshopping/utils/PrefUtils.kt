package ru.isadulla.onlineshopping.utils

import com.orhanobut.hawk.Hawk
import ru.isadulla.onlineshopping.model.CartModel
import ru.isadulla.onlineshopping.model.ProductModel

object PrefUtils {
    const val PREF_FAVORITE = "pref_favorite"
    const val PREF_CART = "pref_cart"

    fun setFavorite(item: ProductModel) {
        val items = Hawk.get(PREF_FAVORITE, arrayListOf<Int>())

        if (items.filter { it == item.id }.firstOrNull() !== null) {
            items.remove(item.id)
        } else {
            items.add(item.id)
        }
        Hawk.put(PREF_FAVORITE, items)
    }

    fun getFavoriteList(): ArrayList<Int> {
        return Hawk.get(PREF_FAVORITE, arrayListOf())
    }

    fun checkFavorite(item: ProductModel): Boolean {
        val items = Hawk.get(PREF_FAVORITE, arrayListOf<Int>())
        return items.filter { it == item.id }.firstOrNull() !== null
    }

    fun setCart(item: ProductModel) {
        val items = Hawk.get<ArrayList<CartModel>>(PREF_CART, arrayListOf())
        val cart = items.filter { it.product_id == item.id }.firstOrNull()
        if (cart !== null) {
            if (item.cart_count > 0) {
                cart.count = item.cart_count
            } else {
                items.remove(cart)
            }
        } else {
            val newCart = CartModel(item.id, item.cart_count)
            items.add(newCart)
        }
        Hawk.put(PREF_CART, items)
    }

    fun getCartList(): ArrayList<CartModel> {
        return Hawk.get(PREF_CART, arrayListOf())
    }

    fun getCartCount(item: ProductModel): Int {
        val items = Hawk.get<ArrayList<CartModel>>(PREF_CART, arrayListOf())
        return items.filter { it.product_id == item.id }.firstOrNull()?.count ?: 0
    }

}