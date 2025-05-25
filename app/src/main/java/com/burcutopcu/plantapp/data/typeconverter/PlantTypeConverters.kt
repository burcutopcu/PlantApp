package com.burcutopcu.plantapp.data.typeconverter

import androidx.room.TypeConverter
import com.burcutopcu.plantapp.data.localmodel.categories.CategoryImageEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class PlantTypeConverters {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val imageAdapter = moshi.adapter(CategoryImageEntity::class.java)

    @TypeConverter
    fun fromImageEntity(imageEntity: CategoryImageEntity): String {
        return imageAdapter.toJson(imageEntity)
    }

    @TypeConverter
    fun toImageEntity(imageJson: String): CategoryImageEntity {
        return imageAdapter.fromJson(imageJson)
            ?: throw IllegalArgumentException("Invalid image JSON")
    }
}