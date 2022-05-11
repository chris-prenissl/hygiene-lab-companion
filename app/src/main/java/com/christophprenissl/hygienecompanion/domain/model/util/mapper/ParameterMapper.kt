package com.christophprenissl.hygienecompanion.domain.model.util.mapper

import com.christophprenissl.hygienecompanion.domain.model.dto.ParameterDto
import com.christophprenissl.hygienecompanion.domain.model.entity.Parameter
import com.christophprenissl.hygienecompanion.domain.model.entity.ParameterType

class ParameterMapper: DataMapper<Parameter, ParameterDto> {
    override fun fromEntity(entity: ParameterDto): Parameter {
        return Parameter(
            name = entity.name,
            value = when(entity.parameterType) {
                ParameterType.Bool.name -> entity.value.toBoolean()
                ParameterType.Number.name -> entity.value?.toInt()?: 0
                ParameterType.Temperature.name -> entity.value?.toFloat()?: 0.0
                else -> entity.value
            },
            parameterType = entity.parameterType?.let { ParameterType.valueOf(it) }
        )
    }

    override fun toEntity(domain: Parameter): ParameterDto {
        return ParameterDto(
            name = domain.name,
            value = domain.value.toString(),
            parameterType = domain.parameterType?.name
        )
    }
}
