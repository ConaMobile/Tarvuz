package com.conamobile.tarvuz.ui.screens.login.di.repository

import android.app.Activity
import com.conamobile.tarvuz.ui.screens.login.di.db.LoginApiService
import com.conamobile.tarvuz.ui.screens.login.phone_number.auth.AuthRepository
import com.conamobile.tarvuz.ui.screens.login.phone_number.auth.AuthRepositoryImpl
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val apiService: LoginApiService,
    private val authRepository: AuthRepositoryImpl,
) {
    suspend fun getUsers() = flow { emit(apiService.getUsers()) }

    fun createUserWithPhone(phone: String, activity: Activity) =
        authRepository.createUserWithPhone(phone, activity)

    fun signInWithCredential(otp: String) = authRepository.signInWithCredential(otp)

}