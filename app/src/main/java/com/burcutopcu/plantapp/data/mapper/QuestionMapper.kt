package com.burcutopcu.plantapp.data.mapper

import com.burcutopcu.plantapp.data.localmodel.questions.QuestionEntity
import com.burcutopcu.plantapp.data.remotemodel.questions.QuestionDto

object QuestionMapper {
    fun mapDtoToEntity(questionsDto: List<QuestionDto>): List<QuestionEntity> {
        return questionsDto.map { dto ->
            QuestionEntity(
                id = dto.id,
                title = dto.title,
                subtitle = dto.subtitle,
                imageUri = dto.imageUri,
                uri = dto.uri,
                order = dto.order
            )
        }
    }
}