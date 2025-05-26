package com.burcutopcu.plantapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.burcutopcu.plantapp.data.localmodel.categories.CategoryImageEntity
import com.burcutopcu.plantapp.data.localmodel.categories.CategoryItemEntity
import com.burcutopcu.plantapp.data.localmodel.questions.QuestionEntity
import com.burcutopcu.plantapp.domain.home.GetCategoriesUseCase
import com.burcutopcu.plantapp.domain.home.GetQuestionsUseCase
import com.burcutopcu.plantapp.ui.features.home.HomeState
import com.burcutopcu.plantapp.ui.features.home.HomeViewModel
import com.burcutopcu.plantapp.ui.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase

    @Mock
    private lateinit var getQuestionsUseCase: GetQuestionsUseCase

    private lateinit var viewModel: HomeViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should be Loading`() = runTest {
        // Given
        `when`(getCategoriesUseCase.getCategories()).thenReturn(flowOf(Resource.Loading()))
        `when`(getQuestionsUseCase.getQuestions()).thenReturn(flowOf(Resource.Loading()))

        // When
        viewModel = HomeViewModel(getCategoriesUseCase, getQuestionsUseCase)

        // Then
        viewModel.uiState.test {
            val initialState = awaitItem()
            assertTrue(initialState is HomeState.Loading)
        }
    }

    @Test
    fun `should emit Success state when both use cases return success`() = runTest {
        // Given
        val categories = listOf(
            CategoryItemEntity(
                id = 1,
                name = "Category 1",
                createdAt = "",
                updatedAt = "",
                publishedAt = "",
                title = "",
                rank = 1,
                image = CategoryImageEntity(
                    id = 1,
                    name = "",
                    alternativeText = "",
                    caption = "",
                    width = 1,
                    height = 1,
                    hash = "",
                    providerMetadata = "",
                    createdAt = "",
                    updatedAt = "",
                    size = 1.0,
                    mime = "",
                    url = "",
                    provider = "",
                    formats = "",
                    ext = "",
                    previewUrl = ""
                )
            ),
            CategoryItemEntity(
                id = 2, name = "Category 2",
                createdAt = "",
                updatedAt = "",
                publishedAt = "",
                title = "",
                rank = 1,
                image = CategoryImageEntity(
                    id = 1,
                    name = "",
                    alternativeText = "",
                    caption = "",
                    width = 1,
                    height = 1,
                    hash = "",
                    providerMetadata = "",
                    createdAt = "",
                    updatedAt = "",
                    size = 1.0,
                    mime = "",
                    url = "",
                    provider = "",
                    formats = "",
                    ext = "",
                    previewUrl = ""
                )
            )
        )
        val questions = listOf(
            QuestionEntity(
                id = 1,
                title = "Question 1",
                subtitle = "",
                imageUri = "",
                uri = "",
                order = 1
            ),
            QuestionEntity(
                id = 2,
                title = "Question 2",
                subtitle = "",
                imageUri = "",
                uri = "",
                order = 1
            )
        )

        `when`(getCategoriesUseCase.getCategories()).thenReturn(
            flowOf(Resource.Success(categories))
        )
        `when`(getQuestionsUseCase.getQuestions()).thenReturn(
            flowOf(Resource.Success(questions))
        )

        // When
        viewModel = HomeViewModel(getCategoriesUseCase, getQuestionsUseCase)

        // Then
        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is HomeState.Success)
            assertEquals(categories, (state as HomeState.Success).categories)
            assertEquals(questions, state.questions)
        }
    }

    @Test
    fun `should emit Error state when categories use case returns error`() = runTest {
        // Given
        val errorMessage = "Categories error occurred"
        `when`(getCategoriesUseCase.getCategories()).thenReturn(
            flowOf(Resource.Error(errorMessage))
        )
        `when`(getQuestionsUseCase.getQuestions()).thenReturn(
            flowOf(Resource.Success(emptyList()))
        )

        // When
        viewModel = HomeViewModel(getCategoriesUseCase, getQuestionsUseCase)

        // Then
        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is HomeState.Error)
            assertEquals(errorMessage, (state as HomeState.Error).message)
        }
    }

    @Test
    fun `should emit Error state when questions use case returns error`() = runTest {
        // Given
        val errorMessage = "Questions error occurred"
        `when`(getCategoriesUseCase.getCategories()).thenReturn(
            flowOf(Resource.Success(emptyList()))
        )
        `when`(getQuestionsUseCase.getQuestions()).thenReturn(
            flowOf(Resource.Error(errorMessage))
        )

        // When
        viewModel = HomeViewModel(getCategoriesUseCase, getQuestionsUseCase)

        // Then
        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is HomeState.Error)
            assertEquals(errorMessage, (state as HomeState.Error).message)
        }
    }

    @Test
    fun `should emit Error state when both use cases return error`() = runTest {
        // Given
        val categoriesError = "Categories error"
        val questionsError = "Questions error"
        `when`(getCategoriesUseCase.getCategories()).thenReturn(
            flowOf(Resource.Error(categoriesError))
        )
        `when`(getQuestionsUseCase.getQuestions()).thenReturn(
            flowOf(Resource.Error(questionsError))
        )

        // When
        viewModel = HomeViewModel(getCategoriesUseCase, getQuestionsUseCase)

        // Then
        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is HomeState.Error)
            assertEquals(categoriesError, (state as HomeState.Error).message)
        }
    }

    @Test
    fun `should emit Loading state when one use case is loading`() = runTest {
        // Given
        `when`(getCategoriesUseCase.getCategories()).thenReturn(
            flowOf(Resource.Loading())
        )
        `when`(getQuestionsUseCase.getQuestions()).thenReturn(
            flowOf(Resource.Success(emptyList()))
        )

        // When
        viewModel = HomeViewModel(getCategoriesUseCase, getQuestionsUseCase)

        // Then
        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is HomeState.Loading)
        }
    }

    @Test
    fun `should emit Error state when exception occurs`() = runTest {
        // Given
        `when`(getCategoriesUseCase.getCategories()).thenThrow(
            RuntimeException("Network error")
        )
        `when`(getQuestionsUseCase.getQuestions()).thenReturn(
            flowOf(Resource.Success(emptyList()))
        )

        // When
        viewModel = HomeViewModel(getCategoriesUseCase, getQuestionsUseCase)

        // Then
        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is HomeState.Error)
            assertEquals("Network error", (state as HomeState.Error).message)
        }
    }

    @Test
    fun `should handle null error message`() = runTest {
        // Given
        `when`(getCategoriesUseCase.getCategories()).thenReturn(
            flowOf(Resource.Error("Categories error occurred"))
        )
        `when`(getQuestionsUseCase.getQuestions()).thenReturn(
            flowOf(Resource.Success(emptyList()))
        )

        // When
        viewModel = HomeViewModel(getCategoriesUseCase, getQuestionsUseCase)

        // Then
        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state is HomeState.Error)
            assertEquals("Categories error occurred", (state as HomeState.Error).message)
        }
    }
}