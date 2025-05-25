package com.burcutopcu.plantapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.burcutopcu.plantapp.data.dao.categories.CategoriesDao
import com.burcutopcu.plantapp.data.dao.questions.QuestionsDao
import com.burcutopcu.plantapp.data.localmodel.categories.CategoryItemEntity
import com.burcutopcu.plantapp.data.localmodel.questions.QuestionEntity
import com.burcutopcu.plantapp.data.typeconverter.PlantTypeConverters

@Database(
    entities = [CategoryItemEntity::class, QuestionEntity::class],
    version = 1,
    exportSchema = false
)
@androidx.room.TypeConverters(PlantTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoriesDao
    abstract fun questionsDao(): QuestionsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}