package com.christophprenissl.hygienecompanion.domain.model.util.mapper

import com.christophprenissl.hygienecompanion.domain.model.dto.SampleLocationDto
import com.christophprenissl.hygienecompanion.domain.model.entity.Address
import com.christophprenissl.hygienecompanion.domain.model.entity.SampleLocation

class SampleLocationMapper(private val address: Address?): DataMapper<SampleLocation, SampleLocationDto> {

    override fun fromEntity(entity: SampleLocationDto): SampleLocation {
        return SampleLocation(
            id = entity.id,
            description = entity.description,
            extraInfo = entity.extraInfo,
            nextHeater = entity.nextHeater,
            address = address
        )
    }

    override fun toEntity(domain: SampleLocation): SampleLocationDto {
        return SampleLocationDto(
            id = domain.id,
            description = domain.description,
            extraInfo = domain.extraInfo,
            nextHeater = domain.nextHeater,
            addressId = domain.address?.id
        )
    }
}
