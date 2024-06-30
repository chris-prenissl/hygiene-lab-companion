package com.christophprenissl.hygienecompanion.model.util.mapper

import com.christophprenissl.hygienecompanion.model.dto.CoveringLetterDto
import com.christophprenissl.hygienecompanion.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.model.entity.SamplingState

class CoveringLetterMapper : DataMapper<CoveringLetter, CoveringLetterDto> {
    private val userMapper = UserMapper()
    private val parameterMapper = ParameterMapper()
    private val sampleMapper = SampleMapper()

    override fun fromEntity(entity: CoveringLetterDto): CoveringLetter {
        return CoveringLetter(
            id = entity.id!!,
            seriesId = entity.seriesId!!,
            description = entity.description ?: "",
            date = entity.date,
            lotId = entity.lotId ?: "",
            laboratoryIn = entity.laboratoryIn,
            resultCreated = entity.resultCreated,
            basicCoveringParameters = entity.basicCoveringParameters?.map {
                parameterMapper.fromEntity(it)
            } ?: emptyList(),
            basicLabReportParameters = entity.basicLabReportParameters?.map {
                parameterMapper.fromEntity(it)
            } ?: emptyList(),
            sampler = entity.sampler?.let { userMapper.fromEntity(it) },
            labWorker = entity.labWorker?.let { userMapper.fromEntity(it) },
            samples = entity.samples?.map {
                sampleMapper.fromEntity(it)
            } ?: emptyList(),
            samplingState = entity.samplingState?.let { SamplingState.valueOf(it) },
        )
    }

    override fun toEntity(domain: CoveringLetter): CoveringLetterDto {
        return CoveringLetterDto(
            id = domain.id,
            seriesId = domain.seriesId,
            description = domain.description,
            date = domain.date,
            lotId = domain.lotId,
            laboratoryIn = domain.laboratoryIn,
            resultCreated = domain.resultCreated,
            basicCoveringParameters = domain.basicCoveringParameters.map {
                parameterMapper.toEntity(it)
            },
            basicLabReportParameters = domain.basicLabReportParameters.map {
                parameterMapper.toEntity(it)
            },
            sampler = domain.sampler?.let { userMapper.toEntity(it) },
            labWorker = domain.labWorker?.let { userMapper.toEntity(it) },
            samples = domain.samples.map {
                sampleMapper.toEntity(it)
            },
            samplingState = domain.samplingState?.name,
        )
    }
}
