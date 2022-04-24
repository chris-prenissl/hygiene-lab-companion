package com.christophprenissl.hygienecompanion.domain.model.util.mapper

import com.christophprenissl.hygienecompanion.domain.model.dto.AddressDto
import com.christophprenissl.hygienecompanion.domain.model.entity.Address

class AddressMapper: DataMapper<Address, AddressDto> {
    override fun fromEntity(entity: AddressDto): Address {
        return Address(
            id = entity.id,
            zip = entity.zip,
            street = entity.street,
            extraInfo = entity.extraInfo
        )
    }

    override fun toEntity(domain: Address): AddressDto {
        return AddressDto(
            id = domain.id,
            zip = domain.zip,
            street = domain.street,
            extraInfo = domain.extraInfo
        )
    }

}