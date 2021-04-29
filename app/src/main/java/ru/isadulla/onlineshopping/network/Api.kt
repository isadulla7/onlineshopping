package ru.isadulla.onlineshopping.network

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.isadulla.onlineshopping.model.BaseResponse
import ru.isadulla.onlineshopping.model.CategoriesModel
import ru.isadulla.onlineshopping.model.OffersModel
import ru.isadulla.onlineshopping.model.ProductModel
import ru.isadulla.onlineshopping.model.request.GetProductsByIdsRequest

interface Api {

    @GET("get_offers")
    fun getOffers(): Observable<BaseResponse<List<OffersModel>>>

    @GET("get_categories")
    fun getCategories(): Observable<BaseResponse<List<CategoriesModel>>>

    @GET("get_top_products")
    fun getProducts(): Observable<BaseResponse<List<ProductModel>>>

    @GET("get_products/{category_id}")
    fun getCategoryProducts(@Path("category_id") categoryId: Int): Observable<BaseResponse<List<ProductModel>>>

    @POST("get_products_by_ids")
    fun getProductsById(@Body request: GetProductsByIdsRequest): Observable<BaseResponse<List<ProductModel>>>

}