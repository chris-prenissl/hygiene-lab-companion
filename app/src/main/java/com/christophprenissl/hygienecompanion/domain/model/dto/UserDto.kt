package com.christophprenissl.hygienecompanion.domain.model.dto


data class UserDto(
    val id: String? = null,
    val name: String? = null,
    val hasCertificate: Boolean? = null,
    val isSamplerOfInstitute: Boolean? = null,
    val userType: String? = null
)
