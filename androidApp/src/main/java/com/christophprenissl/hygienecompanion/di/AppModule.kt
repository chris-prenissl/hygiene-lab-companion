package com.christophprenissl.hygienecompanion.di

import com.christophprenissl.hygienecompanion.data.repository.*
import com.christophprenissl.hygienecompanion.di.util.*
import com.christophprenissl.hygienecompanion.domain.use_case.*
import com.christophprenissl.hygienecompanion.model.repository.*
import com.christophprenissl.hygienecompanion.util.*
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.qualifier.named
import org.koin.dsl.module

@OptIn(ExperimentalCoroutinesApi::class)
val appModule = module {

    single { FirebaseFirestore.getInstance() }

    single { FirebaseCrashlytics.getInstance() }

    single(named(KoinNames.FireStoreRefs.sampleLocation)) {
        get<FirebaseFirestore>().collection(SAMPLE_LOCATIONS_FIRESTORE)
    }

    single(named(KoinNames.FireStoreRefs.address)) {
        get<FirebaseFirestore>().collection(ADDRESSES_FIRESTORE)
    }

    single(named(KoinNames.FireStoreQuery.address)) {
        get<CollectionReference>(named(KoinNames.FireStoreRefs.address)).orderBy("name")
    }
    
    single(named(KoinNames.FireStoreRefs.basis)) {
        get<FirebaseFirestore>().collection(BASES_FIRESTORE)
    }

    single(named(KoinNames.FireStoreRefs.user)) {
        get<FirebaseFirestore>().collection(USERS_FIRESTORE)
    }
    
    single(named(KoinNames.FireStoreRefs.coveringLetterSeries)) {
        get<FirebaseFirestore>().collection(COVERING_LETTERS_FIRESTORE)
    }
    
    single(named(KoinNames.FireStoreRefs.coveringLetter)) {
        get<FirebaseFirestore>().collection(COVERING_LETTERS_FIRESTORE)
    }

    single<SampleLocationRepo> {
        SampleLocationRepoImpl(get(named(KoinNames.FireStoreRefs.sampleLocation)))
    }
    
    single<AddressRepo> {
        AddressRepoImpl(
            get(named(KoinNames.FireStoreRefs.address)), 
            get(named(KoinNames.FireStoreQuery.address))
        )
    }
    
    single<BasisRepo> {
        BasisRepoImpl(get(named(KoinNames.FireStoreRefs.basis)))
    }
    
    single<UserRepo> {
        UserRepoImpl(get(named(KoinNames.FireStoreRefs.user)))
    }

    single<CoveringLetterSeriesRepo> {
        CoveringLetterSeriesRepoImpl(
            get(),
            get(named(KoinNames.FireStoreRefs.coveringLetter)),
            get(named(KoinNames.FireStoreRefs.coveringLetterSeries))
        )
    }
    
    single<CoveringLetterRepo> {
        CoveringLetterRepoImpl(
            get(named(KoinNames.FireStoreRefs.coveringLetter))
        )
    }

    single {
        HygieneCompanionUseCases(
            saveAddress = SaveAddress(get()),
            deleteAddress = DeleteAddress(get()),
            getAddresses = GetAddresses(get()),
            getSampleLocations = GetSampleLocations(get()),
            saveSampleLocation = SaveSampleLocation(get()),
            deleteSampleLocation = DeleteSampleLocation(get()),
            getBases = GetBases(get()),
            saveBasis = SaveBasis(get()),
            deleteBasis = DeleteBasis(get()),
            getCoveringLetterSeriesById = GetCoveringLetterSeriesById(get()),
            createCoveringLetterSeries = CreateCoveringLetterSeries(get()),
            getCoveringLetterSeries = GetCoveringLetterSeries(get()),
            getCoveringLetterSeriesNotEnded = GetCoveringLetterSeriesNotEnded(get()),
            getCoveringLettersNotEnded = GetCoveringLettersNotEnded(get()),
            saveCoveringLetter = SaveCoveringLetter(get()),
            getReports = GetReports(get()),
            createAdditionalCoveringLetters = CreateAdditionalCoveringLetters(get())
        )
    }

    single {
        DataStoreUser(get())
    }
}
