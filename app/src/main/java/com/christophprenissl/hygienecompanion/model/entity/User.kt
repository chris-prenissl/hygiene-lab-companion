package com.christophprenissl.hygienecompanion.model.entity

data class User(
    var id: String = "",
    val name: String,
    val hasCertificate: Boolean = false,
    val isSamplerOfInstitute: Boolean = false,
    val userType: UserType
)
