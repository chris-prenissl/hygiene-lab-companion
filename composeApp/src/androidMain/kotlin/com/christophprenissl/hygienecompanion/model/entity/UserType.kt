package com.christophprenissl.hygienecompanion.model.entity

enum class UserType(val translation: String) {
    HygieneWorker(HYGIENE_WORKER_TRANS),
    Sampler(SAMPLER_TRANS),
    LabWorker(LAB_WORKER_TRANS),
}

const val HYGIENE_WORKER_TRANS = "Hygienearbeiter:in"
const val SAMPLER_TRANS = "Probeentnehmer:in"
const val LAB_WORKER_TRANS = "Laborarbeiter:in"
