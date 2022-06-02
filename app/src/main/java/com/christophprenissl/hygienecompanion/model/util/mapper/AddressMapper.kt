package com.christophprenissl.hygienecompanion.model.util.mapper

import com.christophprenissl.hygienecompanion.model.dto.AddressDto
import com.christophprenissl.hygienecompanion.model.entity.Address

class AddressMapper: DataMapper<Address, AddressDto> {
    override fun fromEntity(entity: AddressDto): Address {
        return Address(
            id = entity.id,
            name = entity.name,
            zip = entity.zip,
            city = entity.city,
            contactName = entity.contactName,
            street = entity.street,
            phone = entity.phone,
            fax = entity.fax,
            eMail = entity.eMail
        )
    }

    override fun toEntity(domain: Address): AddressDto {
        return AddressDto(
            id = domain.id,
            name = domain.name,
            zip = domain.zip,
            city = domain.city,
            contactName = domain.contactName,
            street = domain.street,
            phone = domain.phone,
            fax = domain.fax,
            eMail = domain.eMail
        )
    }
}
