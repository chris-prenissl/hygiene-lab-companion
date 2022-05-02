package com.christophprenissl.hygienecompanion.domain.model.util.mapper

import com.christophprenissl.hygienecompanion.domain.model.dto.CoveringLetterSeriesDto
import com.christophprenissl.hygienecompanion.domain.model.entity.Basis
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetterSeries

class CoveringLetterSeriesMapper(): DataMapper<CoveringLetterSeries, CoveringLetterSeriesDto> {
    override fun fromEntity(entity: CoveringLetterSeriesDto): CoveringLetterSeries {
        val addressMapper = AddressMapper()
        val coveringLetterMapper = CoveringLetterMapper()

        val templateBases = entity.basesByNorm?.map {
            Basis(norm = it)
        }
        return CoveringLetterSeries(
            id = entity.id,
            created = entity.created,
            resultToClient = entity.resultToClient,
            resultToTestingProperty = entity.resultToTestingProperty,
            costLocation = entity.costLocation,
            laboratoryId = entity.laboratoryId,
            bases = templateBases,
            client = entity.client?.let { addressMapper.fromEntity(it) },
            sampleAddress = entity.sampleAddress?.let { addressMapper.fromEntity(it) },
            samplingCompany = entity.samplingCompany?.let { addressMapper.fromEntity(it) },
            coveringLetters = entity.coveringLetters?.map {
                coveringLetterMapper.fromEntity(it)
            },
            endedDate = entity.endedDate
        )
    }

    override fun toEntity(domain: CoveringLetterSeries): CoveringLetterSeriesDto {
        val addressMapper = AddressMapper()
        val coveringLetterMapper = CoveringLetterMapper()
        return CoveringLetterSeriesDto(
            id = domain.id,
            created = domain.created,
            resultToClient = domain.resultToClient,
            resultToTestingProperty = domain.resultToTestingProperty,
            costLocation = domain.costLocation,
            laboratoryId = domain.laboratoryId,
            basesByNorm = domain.bases?.map { it.norm },
            client = domain.client?.let { addressMapper.toEntity(it) },
            sampleAddress = domain.sampleAddress?.let { addressMapper.toEntity(it) },
            samplingCompany = domain.samplingCompany?.let { addressMapper.toEntity(it) },
            coveringLetters = domain.coveringLetters?.map {
                coveringLetterMapper.toEntity(it)
            },
            endedDate = domain.endedDate
        )
    }
}
