package com.christophprenissl.hygienecompanion.di.util

object KoinNames {

    object FireStoreRefs {
        private const val base = "RefFireStore"
        const val sampleLocation = "SampleLocation$base"
        const val address = "Address$base"
        const val basis = "Basis$base"
        const val user = "User$base"
        const val coveringLetterSeries = "CoveringLetterSeries$base"
        const val coveringLetter = "CoveringLetter$base"
    }

    object FireStoreQuery {
        private const val base = "QueryFireStore"
        const val address = "Address$base"
    }
}
