package com.christophprenissl.hygienecompanion.model.util.mapper

import com.christophprenissl.hygienecompanion.model.dto.CoveringLetterSeriesDto
import com.christophprenissl.hygienecompanion.model.entity.Basis
import com.christophprenissl.hygienecompanion.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.model.entity.CoveringLetterSeries
import com.christophprenissl.hygienecompanion.model.entity.SamplingSeriesType

class CoveringLetterSeriesMapper : DataMapper<CoveringLetterSeries, CoveringLetterSeriesDto> {
    override fun fromEntity(entity: CoveringLetterSeriesDto): CoveringLetterSeries {
        val addressMapper = AddressMapper()

        val templateBases = entity.basesByNorm?.map {
            Basis(norm = it!!)
        }
        return CoveringLetterSeries(
            id = entity.id!!,
            created = entity.created,
            description = entity.description ?: "",
            resultToClient = entity.resultToClient ?: false,
            resultToTestingProperty = entity.resultToTestingProperty ?: false,
            costLocation = entity.costLocation ?: "",
            laboratoryId = entity.laboratoryId ?: "",
            bases = templateBases ?: emptyList(),
            client = entity.client?.let { addressMapper.fromEntity(it) },
            sampleAddress = entity.sampleAddress?.let { addressMapper.fromEntity(it) },
            samplingCompany = entity.samplingCompany?.let { addressMapper.fromEntity(it) },
            coveringLetters = entity.coveringLetters?.map {
                CoveringLetter(
                    id = it,
                    seriesId = entity.id,
                )
            } ?: emptyList(),
            plannedStart = entity.plannedStart!!,
            plannedEnd = entity.plannedEnd!!,
            hasEnded = entity.hasEnded ?: false,
            endedDate = entity.endedDate,
            samplingSeriesType = entity.samplingSeriesType?.let {
                SamplingSeriesType.valueOf(it)
            } ?: SamplingSeriesType.NonPeriodic,
        )
    }

    override fun toEntity(domain: CoveringLetterSeries): CoveringLetterSeriesDto {
        val addressMapper = AddressMapper()
        return CoveringLetterSeriesDto(
            id = domain.id,
            created = domain.created,
            description = domain.description,
            resultToClient = domain.resultToClient,
            resultToTestingProperty = domain.resultToTestingProperty,
            costLocation = domain.costLocation,
            laboratoryId = domain.laboratoryId,
            basesByNorm = domain.bases.map { it.norm },
            client = domain.client?.let { addressMapper.toEntity(it) },
            sampleAddress = domain.sampleAddress?.let { addressMapper.toEntity(it) },
            samplingCompany = domain.samplingCompany?.let { addressMapper.toEntity(it) },
            coveringLetters = domain.coveringLetters.map {
                it.id
            },
            plannedStart = domain.plannedStart,
            plannedEnd = domain.plannedEnd,
            hasEnded = domain.hasEnded,
            endedDate = domain.endedDate,
            samplingSeriesType = domain.samplingSeriesType.name,
        )
    }
}
