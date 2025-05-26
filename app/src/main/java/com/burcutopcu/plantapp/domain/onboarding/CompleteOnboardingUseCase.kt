package com.burcutopcu.plantapp.domain.onboarding

import com.burcutopcu.plantapp.data.repo.onboarding.OnboardingRepository
import javax.inject.Inject

class CompleteOnboardingUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return onboardingRepository.setOnboardingCompleted()
    }
}