package com.christophprenissl.hygienecompanion.di

import android.content.Context
import com.christophprenissl.hygienecompanion.data.repository.*
import com.christophprenissl.hygienecompanion.di.util.*
import com.christophprenissl.hygienecompanion.domain.repository.*
import com.christophprenissl.hygienecompanion.domain.use_case.*
import com.christophprenissl.hygienecompanion.util.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    @SampleLocationRefFireStore
    @Provides
    fun provideSampleLocationsRef(db: FirebaseFirestore) = db.collection(SAMPLE_LOCATIONS_FIRESTORE)

    @AddressRefFireStore
    @Provides
    fun provideAddressesRef(db: FirebaseFirestore) = db.collection(ADDRESSES_FIRESTORE)

    @AddressQueryFireStore
    @Provides
    fun provideAddressesQuery(@AddressRefFireStore addressesRef: CollectionReference) = addressesRef.orderBy("name")

    @BasisRefFireStore
    @Provides
    fun provideBasisRef(db: FirebaseFirestore) = db.collection(BASES_FIRESTORE)

    @UserRefFireStore
    @Provides
    fun provideUseRef(db: FirebaseFirestore) = db.collection(USERS_FIRESTORE)

    @CoveringLetterSeriesRefFireStore
    @Provides
    fun provideCoveringLetterSeriesRef(db: FirebaseFirestore) = db.collection(
        COVERING_LETTER_SERIES_FIRESTORE)

    @CoveringLetterRefFireStore
    @Provides
    fun provideCoveringLettersRef(db: FirebaseFirestore) = db.collection(COVERING_LETTERS_FIRESTORE)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    fun provideSampleLocationRepoImpl(
        @SampleLocationRefFireStore sampleLocationsRef: CollectionReference
    ): SampleLocationRepo = SampleLocationRepoImpl(
        sampleLocationsRef = sampleLocationsRef)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    fun provideAddressRepoImpl(
        @AddressRefFireStore addressesRef: CollectionReference,
        @AddressQueryFireStore addressesQuery: Query
    ): AddressRepo = AddressRepoImpl(
        addressesRef = addressesRef,
        addressesQuery = addressesQuery)

    @Provides
    fun provideBasisRepoImpl(
        @BasisRefFireStore basisRef: CollectionReference
    ): BasisRepo = BasisRepoImpl(
        basisRef = basisRef)

    @Provides
    fun provideUserRepoImpl(
        @UserRefFireStore userRef: CollectionReference
    ): UserRepo = UserRepoImpl(
        userRef = userRef
    )

    @Provides
    fun provideCoveringLetterSeriesRepoImpl(
        db: FirebaseFirestore,
        @CoveringLetterRefFireStore coveringLettersRef: CollectionReference,
        @CoveringLetterSeriesRefFireStore coveringLetterSeriesRef: CollectionReference
    ): CoveringLetterSeriesRepo = CoveringLetterSeriesRepoImpl(
        db = db,
        coveringLettersRef = coveringLettersRef,
        coveringLetterSeriesRef = coveringLetterSeriesRef
    )

    @Provides
    fun provideCoveringLetterRepoImpl(
        @CoveringLetterRefFireStore coveringLetterRef: CollectionReference
    ): CoveringLetterRepo = CoveringLetterRepoImpl(
        coveringLetterRef = coveringLetterRef
    )

    @Provides
    fun provideUseCases(
        sampleLocationRepo: SampleLocationRepo,
        addressRepo: AddressRepo,
        basisRepo: BasisRepo,
        coveringLetterSeriesRepo: CoveringLetterSeriesRepo,
        coveringLetterRepo: CoveringLetterRepo
    ) = HygieneCompanionUseCases(
        saveAddress = SaveAddress(addressRepo),
        deleteAddress = DeleteAddress(addressRepo),
        getAddresses = GetAddresses(addressRepo),
        getSampleLocations = GetSampleLocations(sampleLocationRepo),
        saveSampleLocation = SaveSampleLocation(sampleLocationRepo),
        deleteSampleLocation = DeleteSampleLocation(sampleLocationRepo),
        getBases = GetBases(basisRepo),
        saveBasis = SaveBasis(basisRepo),
        deleteBasis = DeleteBasis(basisRepo),
        createCoveringLetterSeries = CreateCoveringLetterSeries(coveringLetterSeriesRepo),
        getCoveringLetterSeries = GetCoveringLetterSeries(coveringLetterSeriesRepo),
        getCoveringLetterSeriesNotEnded = GetCoveringLetterSeriesNotEnded(coveringLetterSeriesRepo),
        getCoveringLettersNotEnded = GetCoveringLettersNotEnded(coveringLetterRepo),
        saveCoveringLetter = SaveCoveringLetter(coveringLetterRepo),
        getReports = GetReports(coveringLetterRepo),
        createAdditionalCoveringLetters = CreateAdditionalCoveringLetters(coveringLetterSeriesRepo)
    )

    @Singleton
    @Provides
    fun provideDataStoreUserType(
        @ApplicationContext app: Context
    ) : DataStoreUserType = DataStoreUserType(app)
}
