package com.christophprenissl.hygienecompanion.presentation.view.logged_out

import com.christophprenissl.hygienecompanion.model.entity.UserType

sealed interface LoggedOutEvent {
    data class UserNameChanged(val name: String): LoggedOutEvent
    data class UserTypeChanged(val type: UserType): LoggedOutEvent
    data class SetCertificateChanged(val hasCertificate: Boolean): LoggedOutEvent
    data class SetSamplerOfInstituteChanged(val isSamplerOfInstitute: Boolean): LoggedOutEvent
    data object Login: LoggedOutEvent
}