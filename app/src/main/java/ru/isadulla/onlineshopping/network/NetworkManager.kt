package ru.isadulla.onlineshopping.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.isadulla.onlineshopping.utils.Const

object NetworkManager {

    var retrofit: Retrofit? = null
    var api: Api? = null
    fun getApiService(): Api {
        val retrofit = Retrofit.Builder().baseUrl(Const.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        api = retrofit.create(Api::class.java)
        return api!!
    }

}