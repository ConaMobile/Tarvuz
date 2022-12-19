package com.conamobile.tarvuz.ui.screens.login.mvvm.db

import android.app.Activity
import com.conamobile.tarvuz.ui.screens.login.mvvm.state.ResultState
import kotlinx.coroutines.flow.Flow

interface LoginApiService {

    fun createUserWithPhone(
        phone: String,
        activity: Activity,
    ): Flow<ResultState<String>>

    fun signInWithCredential(
        otp: String,
    ): Flow<ResultState<String>>

}