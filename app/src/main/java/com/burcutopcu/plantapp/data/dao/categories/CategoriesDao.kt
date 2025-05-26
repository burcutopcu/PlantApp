package com.burcutopcu.plantapp.data.dao.categories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.burcutopcu.plantapp.data.localmodel.categories.CategoryItemEntity

@Dao
interface CategoriesDao {
    @Query("SELECT * FROM categories ORDER BY rank ASC")
    suspend fun getAllCategories(): List<CategoryItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryItemEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryItemEntity)

    @Delete
    suspend fun deleteCategory(category: CategoryItemEntity)

    @Query("DELETE FROM categories")
    suspend fun deleteAllCategories()
}