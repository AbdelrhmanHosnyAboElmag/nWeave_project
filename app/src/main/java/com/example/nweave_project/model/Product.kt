package com.example.nweave_project.model

import com.squareup.moshi.Json

data class Product(
    @Json(name = "description") val description: String,
    @Json(name = "id")val id: String,
    @Json(name = "image_url")val image_url: String,
    @Json(name = "name")val name: String,
    @Json(name = "price")val price: String,
)