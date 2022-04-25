package com.christophprenissl.hygienecompanion.di

import com.christophprenissl.hygienecompanion.data.repository.SampleLocationRepoImpl
import com.christophprenissl.hygienecompanion.domain.repository.SampleLocationRepo
import com.christophprenissl.hygienecompanion.domain.use_case.HygieneCompanionUseCases
import com.christophprenissl.hygienecompanion.domain.use_case.SaveSampleLocation
import com.christophprenissl.hygienecompanion.util.SAMPLE_LOCATIONS_ROUTE
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

    @Provides
    fun provideSampleLocationsRef(db: FirebaseFirestore) = db.collection(SAMPLE_LOCATIONS_ROUTE)

    @Provides
    fun provideSampleLocationQuery(sampleLocationsRef: CollectionReference) = sampleLocationsRef.orderBy("id")

    @OptIn(ExperimentalCoroutinesApi::class)
    @Provides
    fun provideSampleLocationRepoImpl(
        sampleLocationsRef: CollectionReference,
        sampleLocationsQuery: Query
    ): SampleLocationRepo = SampleLocationRepoImpl(
        sampleLocationsRef = sampleLocationsRef,
        sampleLocationsQuery = sampleLocationsQuery)

    @Provides
    fun provideUseCases(sampleLocationRepo: SampleLocationRepo) = HygieneCompanionUseCases(
        saveSampleLocation = SaveSampleLocation(sampleLocationRepo)
    )
}