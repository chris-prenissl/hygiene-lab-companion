package com.christophprenissl.hygienecompanion.domain.model.util.mapper

import com.christophprenissl.hygienecompanion.domain.model.dto.BasisDto
import com.christophprenissl.hygienecompanion.domain.model.entity.Basis
import com.christophprenissl.hygienecompanion.domain.model.entity.ParameterType

class BasisMapper(): DataMapper<Basis, BasisDto> {

    override fun fromEntity(entity: BasisDto): Basis {
        val parameterBasisMapper = ParameterBasisMapper()
        return Basis(
            norm = entity.norm,
            description = entity.description,
            coveringParameters = entity.coveringParameters?.map { parameterBasisMapper.fromEntity(it) },
            coveringSampleParameters = entity.coveringSampleParameters?.map { parameterBasisMapper.fromEntity(it) },
            labSampleParameters = entity.labSampleParameters?.map { parameterBasisMapper.fromEntity(it) },
            labReportParameters = entity.labReportParameters?.map { parameterBasisMapper.fromEntity(it) }
        )
    }

    override fun toEntity(domain: Basis): BasisDto {
        val parameterBasisMapper = ParameterBasisMapper()
        return BasisDto(
            norm = domain.norm,
            description = domain.description,
            coveringParameters = domain.coveringParameters?.map { parameterBasisMapper.toEntity(it) },
            coveringSampleParameters = domain.coveringSampleParameters?.map { parameterBasisMapper.toEntity(it) },
            labSampleParameters = domain.labSampleParameters?.map { parameterBasisMapper.toEntity(it) },
            labReportParameters = domain.labReportParameters?.map { parameterBasisMapper.toEntity(it) }
        )
    }
}
