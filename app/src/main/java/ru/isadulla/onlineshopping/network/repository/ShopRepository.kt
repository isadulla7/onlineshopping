package ru.isadulla.onlineshopping.network.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import ru.isadulla.onlineshopping.model.BaseResponse
import ru.isadulla.onlineshopping.model.CategoriesModel
import ru.isadulla.onlineshopping.model.OffersModel
import ru.isadulla.onlineshopping.model.ProductModel
import ru.isadulla.onlineshopping.model.request.GetProductsByIdsRequest
import ru.isadulla.onlineshopping.network.NetworkManager

class ShopRepository {
    var connet_api = NetworkManager.getApiService()
    private val compositeDisposable = CompositeDisposable()

    fun getOffers(
        error: MutableLiveData<String>,
        success: MutableLiveData<List<OffersModel>>
    ) {
        compositeDisposable.add(
            connet_api.getOffers().subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<OffersModel>>>() {
                    override fun onNext(t: BaseResponse<List<OffersModel>>) {
                        if (t.success) {
                            success.value = t.data
                        } else {
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                    }

                    override fun onComplete() {
                    }
                }
                )
        )
    }

    fun getCategories(
        error: MutableLiveData<String>,
        progress: MutableLiveData<Boolean>, success: MutableLiveData<List<CategoriesModel>>
    ) {
        progress.value = true
        compositeDisposable.add(
            connet_api.getCategories().subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<CategoriesModel>>>() {
                    override fun onNext(t: BaseResponse<List<CategoriesModel>>) {
                        progress.value = false
                        if (t.success) {
                            success.value = t.data
                        } else {
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        progress.value = false
                        error.value = e.localizedMessage
                    }

                    override fun onComplete() {
                    }

                }
                )
        )
    }

    fun getProducts(error: MutableLiveData<String>, success: MutableLiveData<List<ProductModel>>) {
        compositeDisposable.add(
            connet_api.getProducts().subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<ProductModel>>>() {
                    override fun onNext(t: BaseResponse<List<ProductModel>>) {
                        if (t.success) {
                            success.value = t.data
                        } else {
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                    }

                    override fun onComplete() {
                    }
                }
                )
        )
    }

    fun getProductsByCategory(
        id: Int,
        error: MutableLiveData<String>,
        success: MutableLiveData<List<ProductModel>>
    ) {
        compositeDisposable.add(
            connet_api.getCategoryProducts(id).subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<ProductModel>>>() {
                    override fun onNext(t: BaseResponse<List<ProductModel>>) {
                        if (t.success) {
                            success.value = t.data
                        } else {
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                    }

                    override fun onComplete() {
                    }
                }
                )
        )
    }
    fun getProductsByIds(
        ids: List<Int>,
        error: MutableLiveData<String>,
        progress: MutableLiveData<Boolean>,
        success: MutableLiveData<List<ProductModel>>
    ) {
        progress.value=false
        compositeDisposable.add(
            connet_api.getProductsById(GetProductsByIdsRequest(ids)).subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<BaseResponse<List<ProductModel>>>() {
                    override fun onNext(t: BaseResponse<List<ProductModel>>) {
                        if (t.success) {
                            progress.value=false
                            success.value = t.data
                        } else {
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        progress.value=false
                        error.value = e.localizedMessage
                    }

                    override fun onComplete() {
                    }
                }
                )
        )
    }
}