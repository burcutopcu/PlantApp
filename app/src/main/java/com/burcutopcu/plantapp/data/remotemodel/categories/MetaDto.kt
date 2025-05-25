package com.burcutopcu.plantapp.data.remotemodel.categories

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MetaDto(
    @Json(name = "pagination") val pagination: PaginationDto
)