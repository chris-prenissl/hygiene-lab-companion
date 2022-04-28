package com.christophprenissl.hygienecompanion.domain.model.util.mapper

import com.christophprenissl.hygienecompanion.domain.model.dto.ParameterBasisDto
import com.christophprenissl.hygienecompanion.domain.model.entity.ParameterBasis
import com.christophprenissl.hygienecompanion.domain.model.entity.ParameterType

class ParameterBasisMapper: DataMapper<ParameterBasis, ParameterBasisDto> {

    override fun fromEntity(entity: ParameterBasisDto): ParameterBasis {
        return ParameterBasis(
            name = entity.name,
            parameterType = entity.parameterType?.let { ParameterType.valueOf(it) }
        )
    }

    override fun toEntity(domain: ParameterBasis): ParameterBasisDto {
        return ParameterBasisDto(
            name = domain.name,
            parameterType = domain.parameterType?.name
        )
    }
}
