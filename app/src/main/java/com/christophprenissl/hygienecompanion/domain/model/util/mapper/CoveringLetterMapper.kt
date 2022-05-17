package com.christophprenissl.hygienecompanion.domain.model.util.mapper

import com.christophprenissl.hygienecompanion.domain.model.dto.CoveringLetterDto
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.domain.model.entity.SamplingState
import com.christophprenissl.hygienecompanion.domain.model.entity.User

class CoveringLetterMapper: DataMapper<CoveringLetter, CoveringLetterDto> {
    override fun fromEntity(entity: CoveringLetterDto): CoveringLetter {
        val parameterMapper = ParameterMapper()
        val sampleMapper = SampleMapper()
        return CoveringLetter(
            id = entity.id,
            seriesId = entity.seriesId,
            description = entity.description,
            date = entity.date,
            lotId = entity.lotId,
            laboratoryIn = entity.laboratoryIn,
            resultCreated = entity.resultCreated,
            basicCoveringParameters = entity.basicCoveringParameters?.map {
                parameterMapper.fromEntity(it)
            },
            basicLabReportParameters = entity.basicLabReportParameters?.map {
                parameterMapper.fromEntity(it)
            },
            sampler = User(entity.samplerId),
            labWorker = User(entity.labWorkerId),
            samples = entity.samples?.map {
                sampleMapper.fromEntity(it)
            },
            samplingState = entity.samplingState?.let { SamplingState.valueOf(it) }
        )
    }

    override fun toEntity(domain: CoveringLetter): CoveringLetterDto {
        val parameterMapper = ParameterMapper()
        val sampleMapper = SampleMapper()
        return CoveringLetterDto(
            id = domain.id,
            seriesId = domain.seriesId,
            description = domain.description,
            date = domain.date,
            lotId = domain.lotId,
            laboratoryIn = domain.laboratoryIn,
            resultCreated = domain.resultCreated,
            basicCoveringParameters = domain.basicCoveringParameters?.map {
                parameterMapper.toEntity(it)
            },
            basicLabReportParameters = domain.basicLabReportParameters?.map {
                parameterMapper.toEntity(it)
            },
            samplerId = domain.sampler?.id,
            labWorkerId = domain.labWorker?.id,
            samples = domain.samples?.map {
                sampleMapper.toEntity(it)
            },
            samplingState = domain.samplingState?.name
        )
    }
}
