package com.burcutopcu.plantapp.data.remotemodel.categories

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryImageDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "alternativeText") val alternativeText: String?,
    @Json(name = "caption") val caption: String?,
    @Json(name = "width") val width: Int,
    @Json(name = "height") val height: Int,
    @Json(name = "formats") val formats: String?,
    @Json(name = "hash") val hash: String,
    @Json(name = "ext") val ext: String,
    @Json(name = "mime") val mime: String,
    @Json(name = "size") val size: Double,
    @Json(name = "url") val url: String,
    @Json(name = "previewUrl") val previewUrl: String?,
    @Json(name = "provider") val provider: String,
    @Json(name = "provider_metadata") val providerMetadata: String?,
    @Json(name = "createdAt") val createdAt: String,
    @Json(name = "updatedAt") val updatedAt: String
)