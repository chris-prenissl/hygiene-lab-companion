package com.christophprenissl.hygienecompanion.domain.model.entity

enum class SamplingState(val translation: String) {
    Created(CREATED_STATE),
    SamplingInProgress(SAMPLING_IN_PROGRESS_STATE),
    InLaboratory(IN_LABORATORY_STATE),
    LabInProgress(LAB_IN_PROGRESS_STATE),
    SamplesNotAccepted(SAMPLES_NOT_ACCEPTED_STATE),
    LaboratoryResult(LABORATORY_RESULT_STATE)
}

const val CREATED_STATE = "Erstellt"
const val SAMPLING_IN_PROGRESS_STATE = "Probeentnahme aktiv"
const val IN_LABORATORY_STATE = "Im Labor"
const val LAB_IN_PROGRESS_STATE = "Laborauswertung aktiv"
const val SAMPLES_NOT_ACCEPTED_STATE = "Proben nicht akzeptiert"
const val LABORATORY_RESULT_STATE = "Proben befundet"
