package com.christophprenissl.hygienecompanion.model.util.mapper

import com.christophprenissl.hygienecompanion.model.dto.ParameterBasisDto
import com.christophprenissl.hygienecompanion.model.entity.ParameterBasis
import com.christophprenissl.hygienecompanion.model.entity.ParameterType

class ParameterBasisMapper : DataMapper<ParameterBasis, ParameterBasisDto> {

    override fun fromEntity(entity: ParameterBasisDto): ParameterBasis {
        return ParameterBasis(
            name = entity.name!!,
            parameterType = entity.parameterType?.let { ParameterType.valueOf(it) } ?: ParameterType.Note,
        )
    }

    override fun toEntity(domain: ParameterBasis): ParameterBasisDto {
        return ParameterBasisDto(
            name = domain.name,
            parameterType = domain.parameterType.name,
        )
    }
}
