package com.christophprenissl.hygienecompanion.presentation.view.util

import com.christophprenissl.hygienecompanion.domain.model.entity.User
import com.christophprenissl.hygienecompanion.domain.model.entity.UserType
import com.christophprenissl.hygienecompanion.util.DataStoreUser

suspend fun loginAs(
    userDataStore: DataStoreUser,
    user: User,
    onLogin: (UserType) -> Unit
) {
    userDataStore.saveUser(user)
    onLogin(user.userType?: UserType.Sampler)
}
