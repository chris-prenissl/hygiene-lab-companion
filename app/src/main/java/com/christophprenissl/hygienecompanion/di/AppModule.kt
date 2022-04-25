package com.christophprenissl.hygienecompanion.di

import com.christophprenissl.hygienecompanion.util.SAMPLE_LOCATIONS_ROUTE
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    fun provideSampleLocationsRef(db: FirebaseFirestore) = db.collection(SAMPLE_LOCATIONS_ROUTE)

    @Provides
    fun provideBooksQuery(sampleLocationsRef: CollectionReference) = sampleLocationsRef
}