package com.christophprenissl.hygienecompanion.domain.model.util.mapper

import com.christophprenissl.hygienecompanion.domain.model.dto.SamplingSeriesDto
import com.christophprenissl.hygienecompanion.domain.model.entity.Basis
import com.christophprenissl.hygienecompanion.domain.model.entity.SamplingSeries

class SamplingSeriesMapper(private val bases: List<Basis>?): DataMapper<SamplingSeries, SamplingSeriesDto> {
    override fun fromEntity(entity: SamplingSeriesDto): SamplingSeries {
        val addressMapper = AddressMapper()
        return SamplingSeries(
            id = entity.id,
            created = entity.created,
            resultToClient = entity.resultToClient,
            resultToTestingProperty = entity.resultToTestingProperty,
            costLocation = entity.costLocation,
            laboratoryId = entity.laboratoryId,
            bases = bases,
            client = entity.client?.let { addressMapper.fromEntity(it) },
            sampleAddress = entity.sampleAddress?.let { addressMapper.fromEntity(it) },
            samplingCompany = entity.samplingCompany?.let { addressMapper.fromEntity(it) },
            endedDate = entity.endedDate
        )
    }

    override fun toEntity(domain: SamplingSeries): SamplingSeriesDto {
        val addressMapper = AddressMapper()
        return SamplingSeriesDto(
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
            endedDate = domain.endedDate
        )
    }
}
