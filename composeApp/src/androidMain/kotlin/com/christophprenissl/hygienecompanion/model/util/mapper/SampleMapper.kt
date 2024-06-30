package com.christophprenissl.hygienecompanion.model.util.mapper

import com.christophprenissl.hygienecompanion.model.dto.SampleDto
import com.christophprenissl.hygienecompanion.model.entity.Sample

class SampleMapper : DataMapper<Sample, SampleDto> {
    override fun fromEntity(entity: SampleDto): Sample {
        val parameterMapper = ParameterMapper()
        val sampleLocationMapper = SampleLocationMapper(null)
        return Sample(
            id = entity.id!!,
            created = entity.created,
            extraInfoSampling = entity.extraInfoSampling ?: "",
            extraInfoLaboratory = entity.extraInfoLaboratory ?: "",
            warningMessage = entity.warningMessage ?: "",
            coveringSampleParameters = entity.coveringSampleParameters?.map {
                parameterMapper.fromEntity(it)
            } ?: emptyList(),
            labSampleParameters = entity.labSampleParameters?.map {
                parameterMapper.fromEntity(it)
            } ?: emptyList(),
            sampleLocation = entity.sampleLocation?.let { sampleLocationMapper.fromEntity(it) }!!,
        )
    }

    override fun toEntity(domain: Sample): SampleDto {
        val parameterMapper = ParameterMapper()
        val sampleLocationMapper = SampleLocationMapper(null)
        return SampleDto(
            id = domain.id,
            created = domain.created,
            extraInfoSampling = domain.extraInfoSampling,
            extraInfoLaboratory = domain.extraInfoLaboratory,
            warningMessage = domain.warningMessage,
            coveringSampleParameters = domain.coveringSampleParameters.map {
                parameterMapper.toEntity(it)
            },
            labSampleParameters = domain.labSampleParameters.map {
                parameterMapper.toEntity(it)
            },
            sampleLocation = domain.sampleLocation.let { sampleLocationMapper.toEntity(it) },
        )
    }
}
