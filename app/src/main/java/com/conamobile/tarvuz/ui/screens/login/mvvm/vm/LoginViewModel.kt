package com.conamobile.tarvuz.ui.screens.login.mvvm.vm

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.conamobile.tarvuz.ui.screens.login.mvvm.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
) : ViewModel() {
    val phoneNumber = mutableStateOf("")
    val secondTimer = mutableStateOf("")
    val clientName = mutableStateOf("")
    val isFirstNavigation = mutableStateOf(false)

    fun signInWithPhone(number: String, activity: Activity) =
        repository.createUserWithPhone(number, activity)

    fun checkSmsCode(code: String) = repository.signInWithCredential(code)

}