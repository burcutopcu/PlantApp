package com.burcutopcu.plantapp.domain.onboarding

import com.burcutopcu.plantapp.data.repo.onboarding.OnboardingRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckOnboardingUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    operator fun invoke(): Boolean {
        return onboardingRepository.isOnboardingCompleted()
    }
}