package com.example.nweave_project.repository

import android.util.Log
import com.example.nweave_project.model.Product
import com.example.nweave_project.model.ProductModelItem
import com.example.nweave_project.source.api.ProductApi
import com.example.nweave_project.source.local.ProductDatabase
import com.example.nweave_project.source.local.databaseProduct
import com.example.nweave_project.utils.ProductMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val productApi: ProductApi, private val database: databaseProduct
) {
    suspend fun getProduct(): MutableList<ProductModelItem> {
        var product: MutableList<ProductModelItem>
        withContext(Dispatchers.IO) {
            product = productApi.getProduct().await()
            val productEntities = ProductMapper.mapProductToEntity(product = product)
            database.productDao.insertAll(product = *productEntities)
        }
        return product
    }

    suspend fun getProductDatabase(): MutableList<ProductDatabase> {
        var  productDatabase:MutableList<ProductDatabase>
        withContext(Dispatchers.IO) {
           productDatabase=  database.productDao.getAsteroid()
        }
        return productDatabase
    }
}