package com.burcutopcu.plantapp.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burcutopcu.plantapp.domain.home.GetCategoriesUseCase
import com.burcutopcu.plantapp.domain.home.GetQuestionsUseCase
import com.burcutopcu.plantapp.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getQuestionsUseCase: GetQuestionsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeState>(HomeState.Loading)
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                combine(
                    getCategoriesUseCase.getCategories(),
                    getQuestionsUseCase.getQuestions()
                ) { categoriesResult, questionsResult ->
                    when {
                        categoriesResult is Resource.Loading || questionsResult is Resource.Loading -> {
                            HomeState.Loading
                        }

                        categoriesResult is Resource.Error -> {
                            HomeState.Error(categoriesResult.message ?: "Categories error occurred")
                        }

                        questionsResult is Resource.Error -> {
                            HomeState.Error(questionsResult.message ?: "Questions error occurred")
                        }

                        categoriesResult is Resource.Success && questionsResult is Resource.Success -> {
                            HomeState.Success(
                                categories = categoriesResult.data ?: emptyList(),
                                questions = questionsResult.data ?: emptyList()
                            )
                        }

                        else -> {
                            HomeState.Error("Unknown error occurred")
                        }
                    }
                }
                    .catch { e ->
                        emit(HomeState.Error(e.message ?: "Unknown error occurred"))
                    }
                    .collect { state ->
                        _uiState.value = state
                    }

            } catch (e: Exception) {
                _uiState.value = HomeState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}