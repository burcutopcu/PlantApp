package com.burcutopcu.plantapp.data.remotemodel.categories

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoriesDto(
    @Json(name = "data") val data: List<CategoryItemDto>,
    @Json(name = "meta") val meta: MetaDto
)