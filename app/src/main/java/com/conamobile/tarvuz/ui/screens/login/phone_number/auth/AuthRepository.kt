package com.conamobile.tarvuz.ui.screens.login.phone_number.auth

import android.app.Activity
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun createUserWithPhone(
        phone: String,
        activity: Activity,
    ): Flow<ResultState<String>>

    fun signInWithCredential(
        otp: String,
    ): Flow<ResultState<String>>

}