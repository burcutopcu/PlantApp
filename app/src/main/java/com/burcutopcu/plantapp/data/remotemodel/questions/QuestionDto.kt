package com.burcutopcu.plantapp.data.remotemodel.questions

import com.squareup.moshi.Json

data class QuestionDto(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "subtitle") val subtitle: String,
    @Json(name = "image_uri") val imageUri: String,
    @Json(name = "uri") val uri: String,
    @Json(name = "order") val order: Int
)