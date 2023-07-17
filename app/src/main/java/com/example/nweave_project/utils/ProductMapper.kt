package com.example.nweave_project.utils

import com.example.nweave_project.model.Product
import com.example.nweave_project.model.ProductModelItem
import com.example.nweave_project.source.local.ProductDatabase

object ProductMapper {
    fun mapProductToEntity(product: List<ProductModelItem>): Array<ProductDatabase> {
        return product.map { product ->
            ProductDatabase(
                description = product.Product.description,
                image_url = product.Product.image_url,
                name = product.Product.name,
                price = product.Product.price
            )
        }.toTypedArray()
    }
}