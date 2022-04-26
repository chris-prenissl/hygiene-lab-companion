package com.christophprenissl.hygienecompanion.domain.use_case

data class HygieneCompanionUseCases(
    val saveAddress: SaveAddress,
    val getAddresses: GetAddresses,
    val saveSampleLocation: SaveSampleLocation
)