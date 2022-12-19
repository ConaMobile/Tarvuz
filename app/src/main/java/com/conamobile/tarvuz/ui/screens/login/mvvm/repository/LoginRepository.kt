package com.conamobile.tarvuz.ui.screens.login.mvvm.repository

import android.app.Activity
import com.conamobile.tarvuz.ui.screens.login.phone_number.auth.AuthRepositoryImpl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val authRepository: AuthRepositoryImpl,
) {
    fun createUserWithPhone(phone: String, activity: Activity) =
        authRepository.createUserWithPhone(phone, activity)

    fun signInWithCredential(otp: String) = authRepository.signInWithCredential(otp)

}