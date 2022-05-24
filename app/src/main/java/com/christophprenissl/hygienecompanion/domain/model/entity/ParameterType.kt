package com.christophprenissl.hygienecompanion.domain.model.entity

enum class ParameterType(val translation: String) {
    Number(NUMBER_TRANS),
    Temperature(TEMPERATURE_TRANS),
    Bool(BOOL_TRANS),
    Note(NOTE_TRANS)
}

const val NUMBER_TRANS = "Zahl"
const val TEMPERATURE_TRANS = "Temperatur"
const val BOOL_TRANS = "Ja/Nein"
const val NOTE_TRANS = "Text"
