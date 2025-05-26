package com.burcutopcu.plantapp.data.repo.onboarding

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnboardingRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : OnboardingRepository {

    companion object {
        private const val KEY_ONBOARDING_COMPLETED = "onboarding_completed"
    }

    override fun isOnboardingCompleted(): Boolean {
        return try {
            sharedPreferences.getBoolean(KEY_ONBOARDING_COMPLETED, false)
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun setOnboardingCompleted(): Result<Unit> {
        return try {
            sharedPreferences.edit()
                .putBoolean(KEY_ONBOARDING_COMPLETED, true)
                .apply()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(Exception("Failed to save onboarding state: ${e.message}"))
        }
    }
}