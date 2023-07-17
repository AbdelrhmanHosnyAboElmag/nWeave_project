package com.example.nweave_project.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductDatabase(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val description: String,
    val image_url: String,
    val name: String,
    val price: String
)
