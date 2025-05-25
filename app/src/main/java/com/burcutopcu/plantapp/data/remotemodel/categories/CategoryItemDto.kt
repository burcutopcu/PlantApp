package com.burcutopcu.plantapp.data.remotemodel.categories

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryItemDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "createdAt") val createdAt: String,
    @Json(name = "updatedAt") val updatedAt: String,
    @Json(name = "publishedAt") val publishedAt: String,
    @Json(name = "title") val title: String,
    @Json(name = "rank") val rank: Int,
    @Json(name = "image") val image: CategoryImageDto
)