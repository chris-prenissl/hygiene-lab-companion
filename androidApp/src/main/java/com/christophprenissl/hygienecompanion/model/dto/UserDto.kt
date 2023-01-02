package com.christophprenissl.hygienecompanion.model.dto

import com.google.firebase.firestore.DocumentId


data class UserDto(
    @DocumentId
    val id: String? = null,
    val name: String? = null,
    val hasCertificate: Boolean? = null,
    val isSamplerOfInstitute: Boolean? = null,
    val userType: String? = null
)
