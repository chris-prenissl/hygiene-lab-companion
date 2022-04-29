package com.christophprenissl.hygienecompanion.domain.model.util.mapper

import com.christophprenissl.hygienecompanion.domain.model.dto.CoveringLetterDto
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.domain.model.entity.SamplingState
import com.christophprenissl.hygienecompanion.domain.model.entity.User

class CoveringLetterMapper(
    private val sampler: User,
    private val labWorker: User
): DataMapper<CoveringLetter, CoveringLetterDto> {
    override fun fromEntity(entity: CoveringLetterDto): CoveringLetter {
        val parameterMapper = ParameterMapper()
        val sampleMapper = SampleMapper()
        return CoveringLetter(
            id = entity.id,
            created = entity.created,
            lotId = entity.lotId,
            laboratoryIn = entity.laboratoryIn,
            resultCreated = entity.resultCreated,
            coveringParameters = entity.coveringParameters?.map {
                parameterMapper.fromEntity(it)
            },
            labParameters = entity.labParameters?.map {
                parameterMapper.fromEntity(it)
            },
            sampler = sampler,
            labWorker = labWorker,
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
            created = domain.created,
            lotId = domain.lotId,
            laboratoryIn = domain.laboratoryIn,
            resultCreated = domain.resultCreated,
            coveringParameters = domain.coveringParameters?.map {
                parameterMapper.toEntity(it)
            },
            labParameters = domain.labParameters?.map {
                parameterMapper.toEntity(it)
            },
            samplerId = sampler.id,
            labWorkerId = labWorker.id,
            samples = domain.samples?.map {
                sampleMapper.toEntity(it)
            },
            samplingState = domain.samplingState?.name
        )
    }
}
