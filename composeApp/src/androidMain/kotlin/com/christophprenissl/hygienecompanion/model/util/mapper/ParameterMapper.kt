package com.christophprenissl.hygienecompanion.model.util.mapper

import com.christophprenissl.hygienecompanion.model.dto.ParameterDto
import com.christophprenissl.hygienecompanion.model.entity.Parameter
import com.christophprenissl.hygienecompanion.model.entity.ParameterType

class ParameterMapper : DataMapper<Parameter, ParameterDto> {
    override fun fromEntity(entity: ParameterDto): Parameter {
        return Parameter(
            name = entity.name!!,
            value = entity.value ?: "",
            parameterType = entity.parameterType?.let { ParameterType.valueOf(it) } ?: ParameterType.Note,
        )
    }

    override fun toEntity(domain: Parameter): ParameterDto {
        return ParameterDto(
            name = domain.name,
            value = domain.value,
            parameterType = domain.parameterType.name,
        )
    }
}
