package com.christophprenissl.hygienecompanion.domain.model.util.mapper

import com.christophprenissl.hygienecompanion.domain.model.dto.BasisDto
import com.christophprenissl.hygienecompanion.domain.model.entity.Basis
import com.christophprenissl.hygienecompanion.domain.model.entity.ParameterType

class BasisMapper(): DataMapper<Basis, BasisDto> {

    override fun fromEntity(entity: BasisDto): Basis {
        val coveringParametersDto = entity.coveringParameters
        val coveringParameters = hashMapOf<String, ParameterType>()

        coveringParametersDto?.forEach {
            coveringParameters[it.key] = ParameterType.valueOf(it.value)
        }

        val coveringSampleParametersDto = entity.coveringSampleParameters
        val coveringSampleParameters = hashMapOf<String, ParameterType>()

        coveringSampleParametersDto?.forEach {
            coveringSampleParameters[it.key] = ParameterType.valueOf(it.value)
        }

        val labSampleParametersDto = entity.labSampleParameters
        val labSampleParameters = hashMapOf<String, ParameterType>()

        labSampleParametersDto?.forEach {
            labSampleParameters[it.key] = ParameterType.valueOf(it.value)
        }

        val labReportParametersDto = entity.labReportParameters
        val labReportParameters = hashMapOf<String, ParameterType>()

        labReportParametersDto?.forEach {
            labReportParameters[it.key] = ParameterType.valueOf(it.value)
        }

        return Basis(
            norm = entity.norm,
            description = entity.description,
            coveringParameters = coveringParameters,
            coveringSampleParameters = coveringSampleParameters,
            labSampleParameters = labSampleParameters,
            labReportParameters = labReportParameters
        )
    }

    override fun toEntity(domain: Basis): BasisDto {
        val coveringParameters = domain.coveringParameters
        val coveringParametersDto = hashMapOf<String, String>()

        coveringParameters?.forEach {
            coveringParametersDto[it.key] = it.value.name
        }

        val coveringSampleParameters = domain.coveringSampleParameters
        val coveringSampleParametersDto = hashMapOf<String, String>()

        coveringSampleParameters?.forEach {
            coveringSampleParametersDto[it.key] = it.value.name
        }

        val labSampleParameters = domain.labSampleParameters
        val labSampleParametersDto = hashMapOf<String, String>()

        labSampleParameters?.forEach {
            labSampleParametersDto[it.key] = it.value.name
        }

        val labReportParameters = domain.labReportParameters
        val labReportParametersDto = hashMapOf<String, String>()

        labReportParameters?.forEach {
            labReportParametersDto[it.key] = it.value.name
        }

        return BasisDto(
            norm = domain.norm,
            description = domain.description,
            coveringParameters = coveringParametersDto,
            coveringSampleParameters = coveringSampleParametersDto,
            labSampleParameters = labSampleParametersDto,
            labReportParameters = labReportParametersDto
        )
    }
}
