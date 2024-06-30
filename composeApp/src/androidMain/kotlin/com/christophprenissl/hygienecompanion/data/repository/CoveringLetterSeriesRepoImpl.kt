package com.christophprenissl.hygienecompanion.data.repository

import com.christophprenissl.hygienecompanion.model.Response
import com.christophprenissl.hygienecompanion.model.dto.CoveringLetterDto
import com.christophprenissl.hygienecompanion.model.dto.CoveringLetterSeriesDto
import com.christophprenissl.hygienecompanion.model.dto.ParameterDto
import com.christophprenissl.hygienecompanion.model.dto.SampleDto
import com.christophprenissl.hygienecompanion.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.model.entity.CoveringLetterSeries
import com.christophprenissl.hygienecompanion.model.entity.SamplingState
import com.christophprenissl.hygienecompanion.model.repository.CoveringLetterSeriesRepo
import com.christophprenissl.hygienecompanion.model.util.mapper.CoveringLetterMapper
import com.christophprenissl.hygienecompanion.model.util.mapper.CoveringLetterSeriesMapper
import com.christophprenissl.hygienecompanion.util.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoveringLetterSeriesRepoImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val coveringLettersRef: CollectionReference,
    private val coveringLetterSeriesRef: CollectionReference,
) : CoveringLetterSeriesRepo {
    override fun getCoveringLetterSeriesFromDatabase(id: String) = flow {
        try {
            emit(Response.Loading)
            val mapper = CoveringLetterSeriesMapper()
            val coveringLetterSeriesDto = coveringLetterSeriesRef
                .document(id)
                .get()
                .await()
                .toObject(CoveringLetterSeriesDto::class.java)
            if (coveringLetterSeriesDto != null) {
                val coveringLetterSeries = mapper.fromEntity(coveringLetterSeriesDto)
                emit(Response.Success(coveringLetterSeries))
            } else {
                emit(Response.Error("Series not found"))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

    override fun getCoveringLetterSeriesFromDatabase() = callbackFlow {
        val snapshotListener = coveringLetterSeriesRef
            .addSnapshotListener { snapshot, e ->
                val response = if (snapshot != null) {
                    val coveringLetterSeriesDto = snapshot.toObjects(
                        CoveringLetterSeriesDto::class.java,
                    )
                    val mapper = CoveringLetterSeriesMapper()
                    val coveringLetterSeries = coveringLetterSeriesDto.map {
                        mapper.fromEntity(it)
                    }
                    Response.Success(coveringLetterSeries)
                } else {
                    Response.Error(e?.message ?: e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getCoveringLetterSeriesNotEndedFromDatabase() = callbackFlow {
        val snapshotListener = coveringLetterSeriesRef
            .whereEqualTo(HAS_ENDED_FIELD, false)
            .addSnapshotListener { snapshot, e ->
                val response = if (snapshot != null) {
                    val coveringLetterSeriesDto = snapshot.toObjects(
                        CoveringLetterSeriesDto::class.java,
                    )
                    val mapper = CoveringLetterSeriesMapper()
                    val coveringLetterSeries = coveringLetterSeriesDto.map {
                        mapper.fromEntity(it)
                    }
                    Response.Success(coveringLetterSeries)
                } else {
                    Response.Error(e?.message ?: e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun createCoveringLetterSeriesInDatabase(coveringLetterSeries: CoveringLetterSeries) = flow {
        try {
            emit(Response.Loading)
            val coveringLetterSeriesMapper = CoveringLetterSeriesMapper()
            val coveringLetterMapper = CoveringLetterMapper()

            val saved = db.runBatch { batch ->
                val coveringLetterSeriesId = coveringLetterSeriesRef.document().id
                coveringLetterSeries.id = coveringLetterSeriesId
                val coveringLetterSeriesDocument = coveringLetterSeriesRef.document(
                    coveringLetterSeriesId,
                )

                coveringLetterSeries.coveringLetters.forEach {
                    val coveringLetterId = coveringLettersRef.document().id
                    it.id = coveringLetterId
                    it.seriesId = coveringLetterSeriesId
                    val coveringLetterDto = coveringLetterMapper.toEntity(it)
                    val coveringLetterSaveRef = coveringLettersRef.document(coveringLetterId)
                    batch.set(coveringLetterSaveRef, coveringLetterDto)
                }

                val coveringLetterSeriesDto = coveringLetterSeriesMapper.toEntity(
                    coveringLetterSeries,
                )

                batch.set(coveringLetterSeriesDocument, coveringLetterSeriesDto)
            }.await()

            emit(Response.Success(saved))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

    override suspend fun createAdditionalCoveringLettersToSeriesInDatabase(
        coveringLetter: CoveringLetter,
        dates: List<Date>,
    ) = flow {
        val coveringLetterMapper = CoveringLetterMapper()
        val coveringLetterDto = coveringLetterMapper.toEntity(coveringLetter)
        try {
            emit(Response.Loading)

            val saved = db.runBatch { batch ->
                val coveringLetterSeriesDocument = coveringLetterSeriesRef.document(
                    coveringLetter.seriesId,
                )
                dates.forEach { date ->
                    val newId = coveringLettersRef.document().id
                    val newCoveringLetterDto = CoveringLetterDto(
                        id = newId,
                        seriesId = coveringLetter.seriesId,
                        description = coveringLetter.description,
                        date = date,
                        basicCoveringParameters = coveringLetterDto.basicCoveringParameters?.map {
                            ParameterDto(
                                name = it.name,
                                parameterType = it.parameterType,
                            )
                        },
                        basicLabReportParameters = coveringLetterDto.basicLabReportParameters?.map {
                            ParameterDto(
                                name = it.name,
                                parameterType = it.parameterType,
                            )
                        },
                        samples = coveringLetterDto.samples?.map { sample ->
                            SampleDto(
                                id = sample.id,
                                coveringSampleParameters = sample.coveringSampleParameters?.map {
                                    it.copy(value = null)
                                },
                                labSampleParameters = sample.labSampleParameters?.map {
                                    it.copy(value = null)
                                },
                                sampleLocation = sample.sampleLocation,
                            )
                        },
                        samplingState = SamplingState.Created.name,
                    )
                    val newCoveringLetterDocument = coveringLettersRef.document(newId)
                    batch.set(newCoveringLetterDocument, newCoveringLetterDto)
                    batch.update(
                        coveringLetterSeriesDocument,
                        COVERING_LETTERS_ARRAY,
                        FieldValue.arrayUnion(newId),
                    )
                }
            }.await()

            emit(Response.Success(saved))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }
}
