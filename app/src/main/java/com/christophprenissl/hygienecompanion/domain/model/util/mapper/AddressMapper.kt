package com.christophprenissl.hygienecompanion.domain.model.util.mapper

import com.christophprenissl.hygienecompanion.domain.model.dto.AddressDto
import com.christophprenissl.hygienecompanion.domain.model.entity.Address

class AddressMapper: DataMapper<Address, AddressDto> {
    override fun fromEntity(entity: AddressDto): Address {
        return Address(
            id = entity.id,
            name = entity.name,
            zip = entity.zip,
            city = entity.city,
            street = entity.street
        )
    }

    override fun toEntity(domain: Address): AddressDto {
        return AddressDto(
            id = domain.id,
            name = domain.name,
            zip = domain.zip,
            city = domain.city,
            street = domain.street
        )
    }
}
