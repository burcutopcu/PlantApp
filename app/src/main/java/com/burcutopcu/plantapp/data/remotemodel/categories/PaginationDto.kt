package com.burcutopcu.plantapp.data.remotemodel.categories

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginationDto(
    @Json(name = "page") val page: Int,
    @Json(name = "pageSize") val pageSize: Int,
    @Json(name = "pageCount") val pageCount: Int,
    @Json(name = "total") val total: Int
)