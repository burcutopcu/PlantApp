package com.burcutopcu.plantapp.ui.features.onboarding

import androidx.lifecycle.ViewModel
import com.burcutopcu.plantapp.domain.onboarding.CheckOnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val checkOnboardingUseCase: CheckOnboardingUseCase,
) : ViewModel() {

    fun isOnboardingCompleted(): Boolean {
        return checkOnboardingUseCase()
    }
}