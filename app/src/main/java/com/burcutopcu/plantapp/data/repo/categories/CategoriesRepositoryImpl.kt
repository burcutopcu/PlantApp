package com.burcutopcu.plantapp.data.repo.categories

import com.burcutopcu.plantapp.data.dao.categories.CategoriesDao
import com.burcutopcu.plantapp.data.datasource.RemoteDataSource
import com.burcutopcu.plantapp.data.localmodel.categories.CategoryItemEntity
import com.burcutopcu.plantapp.data.mapper.CategoryMapper.mapDtoToEntity
import com.burcutopcu.plantapp.ui.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val categoryDao: CategoriesDao
) : CategoriesRepository {

    override suspend fun getCategories(): Flow<Resource<List<CategoryItemEntity>>> = flow {
        emit(Resource.Loading())

        try {
            val localCategories = categoryDao.getAllCategories()
            if (localCategories.isNotEmpty()) {
                emit(Resource.Success(localCategories))
            }

            val response = remoteDataSource.service.getCategories()
            if (response.isSuccessful) {
                response.body()?.let { categoriesResponse ->
                    val categoryEntities = mapDtoToEntity(categoriesResponse.data)
                    categoryDao.insertCategories(categoryEntities)
                    emit(Resource.Success(categoryDao.getAllCategories()))
                } ?: throw Exception("Response body is null")
            } else {
                throw Exception("HTTP ${response.code()}: ${response.message()}")
            }

        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error occurred"))
        }
    }
}