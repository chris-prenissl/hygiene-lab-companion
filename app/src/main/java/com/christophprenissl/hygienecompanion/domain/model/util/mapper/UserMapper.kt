package com.christophprenissl.hygienecompanion.domain.model.util.mapper

import com.christophprenissl.hygienecompanion.domain.model.dto.UserDto
import com.christophprenissl.hygienecompanion.domain.model.entity.User
import com.christophprenissl.hygienecompanion.domain.model.entity.UserType

class UserMapper: DataMapper<User, UserDto> {
    override fun fromEntity(entity: UserDto): User {
        return User(
            id = entity.id,
            name = entity.name,
            hasCertificate = entity.hasCertificate,
            isSamplerOfInstitute = entity.isSamplerOfInstitute,
            userType = entity.userType?.let { UserType.valueOf(it) }
        )
    }

    override fun toEntity(domain: User): UserDto {
        return UserDto(
            id = domain.id,
            name = domain.name,
            hasCertificate = domain.hasCertificate,
            isSamplerOfInstitute = domain.isSamplerOfInstitute,
            userType = domain.userType?.name
        )
    }
}
