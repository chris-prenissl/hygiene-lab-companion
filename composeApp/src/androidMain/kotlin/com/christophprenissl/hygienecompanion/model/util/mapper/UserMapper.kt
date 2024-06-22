package com.christophprenissl.hygienecompanion.model.util.mapper

import com.christophprenissl.hygienecompanion.model.dto.UserDto
import com.christophprenissl.hygienecompanion.model.entity.User
import com.christophprenissl.hygienecompanion.model.entity.UserType

class UserMapper: DataMapper<User, UserDto> {
    override fun fromEntity(entity: UserDto): User {
        return User(
            id = entity.id ?: "",
            name = entity.name!!,
            hasCertificate = entity.hasCertificate ?: false,
            isSamplerOfInstitute = entity.isSamplerOfInstitute ?: false,
            userType = entity.userType?.let { UserType.valueOf(it) } ?: UserType.Sampler
        )
    }

    override fun toEntity(domain: User): UserDto {
        return UserDto(
            id = domain.id,
            name = domain.name,
            hasCertificate = domain.hasCertificate,
            isSamplerOfInstitute = domain.isSamplerOfInstitute,
            userType = domain.userType.name
        )
    }
}
