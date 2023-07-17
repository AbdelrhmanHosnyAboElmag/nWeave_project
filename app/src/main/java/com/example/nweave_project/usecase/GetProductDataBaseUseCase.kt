package com.example.nweave_project.usecase

import com.example.nweave_project.repository.ProductRepository
import com.example.nweave_project.source.local.ProductDatabase
import com.example.nweave_project.utils.DataResult
import javax.inject.Inject

class GetProductDataBaseUseCase  @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend  fun getProductFromDatabase(): DataResult<MutableList<ProductDatabase>> {
        return try {
            DataResult.success(productRepository.getProductDatabase())
        }catch (e:Exception){
            DataResult.error(e)
        }
    }
}