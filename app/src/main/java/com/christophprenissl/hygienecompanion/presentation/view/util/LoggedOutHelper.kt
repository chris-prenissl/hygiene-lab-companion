package com.christophprenissl.hygienecompanion.presentation.view.util

import com.christophprenissl.hygienecompanion.domain.model.entity.UserType
import com.christophprenissl.hygienecompanion.util.DataStoreUserType

suspend fun loginAs(
    userTypeStore: DataStoreUserType,
    userType: UserType,
    onLogin: (UserType) -> Unit
) {
    userTypeStore.saveUserType(userType)
    onLogin(userType)
}
