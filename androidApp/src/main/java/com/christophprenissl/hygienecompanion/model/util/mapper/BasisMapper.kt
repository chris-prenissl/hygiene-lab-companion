package com.christophprenissl.hygienecompanion.model.util.mapper

import com.christophprenissl.hygienecompanion.model.dto.BasisDto
import com.christophprenissl.hygienecompanion.model.entity.Basis

class BasisMapper(): DataMapper<Basis, BasisDto> {

    override fun fromEntity(entity: BasisDto): Basis {
        val parameterBasisMapper = ParameterBasisMapper()
        return Basis(
            norm = entity.norm ?: "",
            description = entity.description ?: "",
            basicCoveringParameters = entity.basicCoveringParameters?.map { parameterBasisMapper.fromEntity(it) } ?: emptyList(),
            coveringSampleParameters = entity.coveringSampleParameters?.map { parameterBasisMapper.fromEntity(it) } ?: emptyList(),
            labSampleParameters = entity.labSampleParameters?.map { parameterBasisMapper.fromEntity(it) } ?: emptyList(),
            basicLabReportParameters = entity.basicLabReportParameters?.map { parameterBasisMapper.fromEntity(it) } ?: emptyList()
        )
    }

    override fun toEntity(domain: Basis): BasisDto {
        val parameterBasisMapper = ParameterBasisMapper()
        return BasisDto(
            norm = domain.norm,
            description = domain.description,
            basicCoveringParameters = domain.basicCoveringParameters.map { parameterBasisMapper.toEntity(it) },
            coveringSampleParameters = domain.coveringSampleParameters.map { parameterBasisMapper.toEntity(it) },
            labSampleParameters = domain.labSampleParameters.map { parameterBasisMapper.toEntity(it) },
            basicLabReportParameters = domain.basicLabReportParameters.map { parameterBasisMapper.toEntity(it) }
        )
    }
}
