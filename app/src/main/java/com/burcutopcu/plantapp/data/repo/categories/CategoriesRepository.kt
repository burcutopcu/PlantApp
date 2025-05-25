package com.burcutopcu.plantapp.data.repo.categories

import com.burcutopcu.plantapp.data.localmodel.categories.CategoryItemEntity
import com.burcutopcu.plantapp.ui.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun getCategories(): Flow<Resource<List<CategoryItemEntity>>>
    suspend fun getCategoryById(id: Int): Flow<Resource<CategoryItemEntity>>
    suspend fun refreshCategories(): Result<Unit>
}