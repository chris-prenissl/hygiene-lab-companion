package com.christophprenissl.hygienecompanion.presentation.view.util

import com.christophprenissl.hygienecompanion.domain.model.entity.SamplingState
import com.christophprenissl.hygienecompanion.domain.model.entity.UserType

fun isUserAllowedToEnter(
    userType: UserType?,
    samplingState: SamplingState?
): Boolean {
    return when (userType) {
        UserType.HygieneWorker, UserType.Sampler -> {
            samplingState != SamplingState.InLaboratory
                    && samplingState != SamplingState.LabInProgress
                    && samplingState != null
        }
        UserType.LabWorker -> {
            samplingState == SamplingState.LabInProgress
                    || samplingState == SamplingState.InLaboratory
        }
        else -> false
    }
}
