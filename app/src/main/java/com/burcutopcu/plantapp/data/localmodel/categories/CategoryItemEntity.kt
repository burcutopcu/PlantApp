package com.burcutopcu.plantapp.data.localmodel.categories

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryItemEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val createdAt: String,
    val updatedAt: String,
    val publishedAt: String,
    val title: String,
    val rank: Int,
    val image: CategoryImageEntity
)