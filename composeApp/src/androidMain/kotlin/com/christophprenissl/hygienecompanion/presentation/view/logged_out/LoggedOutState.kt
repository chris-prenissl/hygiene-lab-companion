package com.christophprenissl.hygienecompanion.presentation.view.logged_out

import com.christophprenissl.hygienecompanion.model.entity.UserType

data class LoggedOutState(
    val name: String? = null,
    val hasCertificate: Boolean = false,
    val isUserOfInstitute: Boolean = false,
    val userType: UserType? = null,
)
