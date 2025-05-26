package com.burcutopcu.plantapp.data.repo.onboarding

interface OnboardingRepository {
    fun isOnboardingCompleted(): Boolean
    suspend fun setOnboardingCompleted(): Result<Unit>
}