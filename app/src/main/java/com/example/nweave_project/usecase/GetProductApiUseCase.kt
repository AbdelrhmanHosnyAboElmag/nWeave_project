package com.example.nweave_project.usecase

import android.util.Log
import com.example.nweave_project.model.Product
import com.example.nweave_project.model.ProductModelItem
import com.example.nweave_project.repository.ProductRepository
import com.example.nweave_project.source.local.ProductDatabase
import com.example.nweave_project.utils.DataResult
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend  fun getProductFromApi(): DataResult<MutableList<ProductModelItem>> {
        return try {
            DataResult.success(productRepository.getProduct())
        }catch (e:Exception){
            DataResult.error(e)
        }
    }


}