package com.burcutopcu.plantapp.domain.home

import com.burcutopcu.plantapp.data.repo.categories.CategoriesRepository
import com.burcutopcu.plantapp.data.localmodel.categories.CategoryItemEntity
import com.burcutopcu.plantapp.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) {
    suspend fun getCategories(): Flow<Resource<List<CategoryItemEntity>>> {
        return categoriesRepository.getCategories()
    }
}