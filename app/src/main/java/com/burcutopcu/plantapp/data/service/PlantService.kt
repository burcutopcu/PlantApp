package com.burcutopcu.plantapp.data.service

import com.burcutopcu.plantapp.data.remotemodel.categories.CategoriesDto
import com.burcutopcu.plantapp.data.remotemodel.questions.QuestionDto
import retrofit2.Response
import retrofit2.http.GET

interface PlantService {
    companion object {
        const val BASE_URL: String = "https://dummy-api-jtg6bessta-ey.a.run.app/"
    }

    @GET("getCategories")
    suspend fun getCategories(): Response<CategoriesDto>

    @GET("getQuestions")
    suspend fun getQuestions(): Response<List<QuestionDto>>
}