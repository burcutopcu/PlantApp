package com.burcutopcu.plantapp.ui.features.home

import com.burcutopcu.plantapp.data.localmodel.categories.CategoryItemEntity
import com.burcutopcu.plantapp.data.localmodel.questions.QuestionEntity

sealed class HomeState {
    data object Loading : HomeState()
    data class Success(
        val categories: List<CategoryItemEntity>,
        val questions: List<QuestionEntity>
    ) : HomeState()

    data class Error(val message: String) : HomeState()
}