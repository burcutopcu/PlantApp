package com.burcutopcu.plantapp.di

import android.content.Context
import androidx.room.Room
import com.burcutopcu.plantapp.data.AppDatabase
import com.burcutopcu.plantapp.data.dao.categories.CategoriesDao
import com.burcutopcu.plantapp.data.dao.questions.QuestionsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCategoriesDao(database: AppDatabase): CategoriesDao {
        return database.categoryDao()
    }

    @Singleton
    @Provides
    fun provideQuestionsDao(database: AppDatabase): QuestionsDao {
        return database.questionsDao()
    }
}