package com.christophprenissl.hygienecompanion.domain.use_case

data class HygieneCompanionUseCases(
    val saveAddress: SaveAddress,
    val deleteAddress: DeleteAddress,
    val getAddresses: GetAddresses,
    val getSampleLocations: GetSampleLocations,
    val saveSampleLocation: SaveSampleLocation,
    val deleteSampleLocation: DeleteSampleLocation,
    val getBases: GetBases,
    val saveBasis: SaveBasis,
    val deleteBasis: DeleteBasis,
    val getCoveringLetterSeriesById: GetCoveringLetterSeriesById,
    val createCoveringLetterSeries: CreateCoveringLetterSeries,
    val getCoveringLetterSeries: GetCoveringLetterSeries,
    val getCoveringLetterSeriesNotEnded: GetCoveringLetterSeriesNotEnded,
    val getCoveringLettersNotEnded: GetCoveringLettersNotEnded,
    val saveCoveringLetter: SaveCoveringLetter,
    val getReports: GetReports,
    val createAdditionalCoveringLetters: CreateAdditionalCoveringLetters
)
