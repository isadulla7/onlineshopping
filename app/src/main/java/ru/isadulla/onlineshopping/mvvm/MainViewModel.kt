package ru.isadulla.onlineshopping.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.isadulla.onlineshopping.model.CategoriesModel
import ru.isadulla.onlineshopping.model.OffersModel
import ru.isadulla.onlineshopping.model.ProductModel
import ru.isadulla.onlineshopping.network.repository.ShopRepository

class MainViewModel : ViewModel() {
    private val repository = ShopRepository()
    val error = MutableLiveData<String>()
    val progress = MutableLiveData<Boolean>()
    val offersData = MutableLiveData<List<OffersModel>>()
    val categoriesData = MutableLiveData<List<CategoriesModel>>()
    val productData = MutableLiveData<List<ProductModel>>()

    fun getOffers() {
        repository.getOffers(error, offersData)
    }

    fun getCategories() {
        repository.getCategories(error, progress, categoriesData)
    }

    fun getProducts() {
        repository.getProducts(error, productData)
    }

    fun getProductsByCategory(id: Int) {
        repository.getProductsByCategory(id, error, productData)
    }

    fun getProductsByIds(ids: List<Int>) {
        repository.getProductsByIds(ids, error,progress, productData)
    }


}

