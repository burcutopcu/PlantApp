package com.burcutopcu.plantapp.data.repo.questions

import com.burcutopcu.plantapp.data.localmodel.questions.QuestionEntity
import com.burcutopcu.plantapp.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

interface QuestionsRepository {
    suspend fun getQuestions(): Flow<Resource<List<QuestionEntity>>>
    suspend fun getQuestionById(id: Int): Flow<Resource<QuestionEntity>>
    suspend fun refreshQuestions(): Result<Unit>
}