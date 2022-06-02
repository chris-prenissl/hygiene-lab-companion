package com.christophprenissl.hygienecompanion.model.entity

data class User(
    var id: String? = null,
    val name: String? = null,
    val hasCertificate: Boolean? = null,
    val isSamplerOfInstitute: Boolean? = null,
    val userType: UserType? = null
)
