package com.burcutopcu.plantapp.domain.home

import com.burcutopcu.plantapp.data.localmodel.questions.QuestionEntity
import com.burcutopcu.plantapp.data.repo.questions.QuestionsRepository
import com.burcutopcu.plantapp.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuestionsUseCase @Inject constructor(
    private val questionsRepository: QuestionsRepository
) {
    suspend fun getQuestions(): Flow<Resource<List<QuestionEntity>>> {
        return questionsRepository.getQuestions()
    }
}