package com.example.nweave_project.source.api

import com.example.nweave_project.model.ProductModelItem
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


interface ProductApi {
    @GET("uploads/2012/09/featured.txt")
    fun getProduct(): Deferred<MutableList<ProductModelItem>>
}


