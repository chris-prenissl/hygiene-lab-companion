package com.christophprenissl.hygienecompanion.domain.model.util.mapper

import com.christophprenissl.hygienecompanion.domain.model.dto.ParameterDto
import com.christophprenissl.hygienecompanion.domain.model.entity.Parameter
import com.christophprenissl.hygienecompanion.domain.model.entity.ParameterType

class ParameterMapper: DataMapper<Parameter, ParameterDto> {
    override fun fromEntity(entity: ParameterDto): Parameter {
        return Parameter(
            name = entity.name,
            value = entity.value,
            parameterType = entity.parameterType?.let { ParameterType.valueOf(it) }
        )
    }

    override fun toEntity(domain: Parameter): ParameterDto {
        return ParameterDto(
            name = domain.name,
            value = domain.value,
            parameterType = domain.parameterType?.name
        )
    }
}
