package com.christophprenissl.hygienecompanion.di

import com.christophprenissl.hygienecompanion.data.repository.*
import com.christophprenissl.hygienecompanion.di.util.*
import com.christophprenissl.hygienecompanion.domain.repository.AddressRepo
import com.christophprenissl.hygienecompanion.domain.repository.BasisRepo
import com.christophprenissl.hygienecompanion.domain.repository.CoveringLetterSeriesRepo
import com.christophprenissl.hygienecompanion.domain.repository.SampleLocationRepo
import com.christophprenissl.hygienecompanion.domain.use_case.*
import com.christophprenissl.hygienecompanion.util.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    @SampleLocationRefFireStore
    @Provides
    fun provideSampleLocationsRef(db: FirebaseFirestore) = db.collection(SAMPLE_LOCATIONS_FIRESTORE)

    @SampleLocationQueryFireStore
    @Provides
    fun provideSampleLocationQuery(@SampleLocationRefFireStore sampleLocationsRef: CollectionReference) = sampleLocationsRef.orderBy("description")

    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    fun provideSampleLocationRepoImpl(
        @SampleLocationRefFireStore sampleLocationsRef: CollectionReference,
        @SampleLocationQueryFireStore sampleLocationsQuery: Query
    ): SampleLocationRepo = SampleLocationRepoImpl(
        sampleLocationsRef = sampleLocationsRef,
        sampleLocationsQuery = sampleLocationsQuery)

    @AddressRefFireStore
    @Provides
    fun provideAddressesRef(db: FirebaseFirestore) = db.collection(ADDRESSES_FIRESTORE)

    @AddressQueryFireStore
    @Provides
    fun provideAddressesQuery(@AddressRefFireStore addressesRef: CollectionReference) = addressesRef.orderBy("name")

    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    fun provideAddressRepoImpl(
        @AddressRefFireStore addressesRef: CollectionReference,
        @AddressQueryFireStore addressesQuery: Query
    ): AddressRepo = AddressRepoImpl(
        addressesRef = addressesRef,
        addressesQuery = addressesQuery)

    @BasisRefFireStore
    @Provides
    fun provideBasisRef(db: FirebaseFirestore) = db.collection(BASES_FIRESTORE)

    @Provides
    fun provideBasisRepoImpl(
        @BasisRefFireStore basisRef: CollectionReference
    ): BasisRepo = BasisRepoImpl(
        basisRef = basisRef)

    @UserRefFireStore
    @Provides
    fun provideUseRef(db: FirebaseFirestore) = db.collection(USERS_FIRESTORE)

    @Provides
    fun provideUserRepoImpl(
        @UserRefFireStore userRef: CollectionReference
    ): UserRepoImpl = UserRepoImpl(
        userRef = userRef
    )

    @CoveringLetterSeriesRefFireStore
    @Provides
    fun provideCoveringLetterSeriesRef(db: FirebaseFirestore) = db.collection(
        COVERING_LETTER_SERIES_FIRESTORE)

    @Provides
    fun provideCoveringLetterRepoImpl(
        @CoveringLetterSeriesRefFireStore coveringLetterSeriesRef: CollectionReference
    ): CoveringLetterRepoImpl = CoveringLetterRepoImpl(
        coveringLetterSeriesRef = coveringLetterSeriesRef
    )

    @Provides
    fun provideUseCases(
        sampleLocationRepo: SampleLocationRepo,
        addressRepo: AddressRepo,
        basisRepo: BasisRepo
    ) = HygieneCompanionUseCases(
        saveAddress = SaveAddress(addressRepo),
        deleteAddress = DeleteAddress(addressRepo),
        getAddresses = GetAddresses(addressRepo),
        getSampleLocations = GetSampleLocations(sampleLocationRepo),
        saveSampleLocation = SaveSampleLocation(sampleLocationRepo),
        deleteSampleLocation = DeleteSampleLocation(sampleLocationRepo),
        getBases = GetBases(basisRepo),
        saveBasis = SaveBasis(basisRepo),
        deleteBasis = DeleteBasis(basisRepo)
    )
}
