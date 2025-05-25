package com.burcutopcu.plantapp.data.localmodel.categories

data class CategoryImageEntity(
    val id: Int,
    val name: String,
    val alternativeText: String?,
    val caption: String?,
    val width: Int,
    val height: Int,
    val formats: String?,
    val hash: String,
    val ext: String,
    val mime: String,
    val size: Double,
    val url: String,
    val previewUrl: String?,
    val provider: String,
    val providerMetadata: String?,
    val createdAt: String,
    val updatedAt: String
)