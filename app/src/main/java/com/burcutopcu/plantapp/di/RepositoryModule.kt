package com.burcutopcu.plantapp.di

import android.content.SharedPreferences
import com.burcutopcu.plantapp.data.repo.categories.CategoriesRepository
import com.burcutopcu.plantapp.data.repo.categories.CategoriesRepositoryImpl
import com.burcutopcu.plantapp.data.dao.categories.CategoriesDao
import com.burcutopcu.plantapp.data.dao.questions.QuestionsDao
import com.burcutopcu.plantapp.data.datasource.RemoteDataSource
import com.burcutopcu.plantapp.data.repo.onboarding.OnboardingRepository
import com.burcutopcu.plantapp.data.repo.onboarding.OnboardingRepositoryImpl
import com.burcutopcu.plantapp.data.repo.questions.QuestionsRepository
import com.burcutopcu.plantapp.data.repo.questions.QuestionsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideCategoriesRepository(
        remoteDataSource: RemoteDataSource,
        categoriesDao: CategoriesDao,
    ): CategoriesRepository {
        return CategoriesRepositoryImpl(
            remoteDataSource,
            categoriesDao,
        )
    }

    @Singleton
    @Provides
    fun provideQuestionsRepository(
        remoteDataSource: RemoteDataSource,
        questionsDao: QuestionsDao,
    ): QuestionsRepository {
        return QuestionsRepositoryImpl(
            remoteDataSource,
            questionsDao
        )
    }

    @Singleton
    @Provides
    fun provideOnboardingRepository(
        sharedPreferences: SharedPreferences
    ): OnboardingRepository {
        return OnboardingRepositoryImpl(
            sharedPreferences
        )
    }
}