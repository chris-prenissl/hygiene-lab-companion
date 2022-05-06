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
            description = entity.description,
            date = entity.date,
            lotId = entity.lotId,
            laboratoryIn = entity.laboratoryIn,
            resultCreated = entity.resultCreated,
            coveringParameters = entity.coveringParameters?.map {
                parameterMapper.fromEntity(it)
            },
            labParameters = entity.labParameters?.map {
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
            description = domain.description,
            date = domain.date,
            lotId = domain.lotId,
            laboratoryIn = domain.laboratoryIn,
            resultCreated = domain.resultCreated,
            coveringParameters = domain.coveringParameters?.map {
                parameterMapper.toEntity(it)
            },
            labParameters = domain.labParameters?.map {
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
