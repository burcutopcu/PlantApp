package com.burcutopcu.plantapp.data.mapper

import com.burcutopcu.plantapp.data.localmodel.categories.CategoryImageEntity
import com.burcutopcu.plantapp.data.localmodel.categories.CategoryItemEntity
import com.burcutopcu.plantapp.data.remotemodel.categories.CategoryImageDto
import com.burcutopcu.plantapp.data.remotemodel.categories.CategoryItemDto

object CategoryMapper {
    fun mapDtoToEntity(categoriesDto: List<CategoryItemDto>): List<CategoryItemEntity> {
        return categoriesDto.map { dto ->
            CategoryItemEntity(
                id = dto.id,
                name = dto.name,
                createdAt = dto.createdAt,
                updatedAt = dto.updatedAt,
                publishedAt = dto.publishedAt,
                title = dto.title,
                rank = dto.rank,
                image = mapImageDtoToEntity(dto.image)
            )
        }
    }

    private fun mapImageDtoToEntity(imageDto: CategoryImageDto): CategoryImageEntity {
        return CategoryImageEntity(
            id = imageDto.id,
            name = imageDto.name,
            alternativeText = imageDto.alternativeText,
            caption = imageDto.caption,
            width = imageDto.width,
            height = imageDto.height,
            formats = imageDto.formats,
            hash = imageDto.hash,
            ext = imageDto.ext,
            mime = imageDto.mime,
            size = imageDto.size,
            url = imageDto.url,
            previewUrl = imageDto.previewUrl,
            provider = imageDto.provider,
            providerMetadata = imageDto.providerMetadata,
            createdAt = imageDto.createdAt,
            updatedAt = imageDto.updatedAt
        )
    }
}