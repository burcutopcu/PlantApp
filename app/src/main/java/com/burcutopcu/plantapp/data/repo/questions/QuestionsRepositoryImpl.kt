package com.burcutopcu.plantapp.data.repo.questions

import com.burcutopcu.plantapp.data.dao.questions.QuestionsDao
import com.burcutopcu.plantapp.data.datasource.RemoteDataSource
import com.burcutopcu.plantapp.data.localmodel.questions.QuestionEntity
import com.burcutopcu.plantapp.data.mapper.QuestionMapper
import com.burcutopcu.plantapp.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuestionsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val questionsDao: QuestionsDao
) : QuestionsRepository {

    override suspend fun getQuestions(): Flow<Resource<List<QuestionEntity>>> = flow {
        try {
            emit(Resource.Loading())

            val localQuestions = questionsDao.getAllQuestions()

            if (localQuestions.isNotEmpty()) {
                emit(Resource.Success(localQuestions))

                try {
                    val response = remoteDataSource.service.getQuestions()
                    if (response.isSuccessful) {
                        response.body()?.let { questionsResponse ->
                            val questionEntities = QuestionMapper.mapDtoToEntity(questionsResponse)
                            questionsDao.insertQuestions(questionEntities)
                            emit(Resource.Success(questionEntities))
                        }
                    }
                } catch (e: Exception) {
                    emit(Resource.Success(localQuestions))
                }
            } else {
                val response = remoteDataSource.service.getQuestions()
                if (response.isSuccessful) {
                    response.body()?.let { questionsResponse ->
                        val questionEntities = QuestionMapper.mapDtoToEntity(questionsResponse)
                        questionsDao.insertQuestions(questionEntities)
                        emit(Resource.Success(questionEntities))
                    } ?: emit(Resource.Error("Response body is null"))
                } else {
                    emit(Resource.Error("HTTP ${response.code()}: ${response.message()}"))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown error occurred"))
        }
    }
}