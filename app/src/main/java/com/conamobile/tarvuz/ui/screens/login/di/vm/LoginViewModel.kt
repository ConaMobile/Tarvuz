package com.conamobile.tarvuz.ui.screens.login.di.vm

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.conamobile.tarvuz.ui.screens.login.di.model.GithubUser
import com.conamobile.tarvuz.ui.screens.login.di.repository.LoginRepository
import com.conamobile.tarvuz.util.MySharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    @ApplicationContext application: Context,
) : ViewModel() {
    val userList = MutableStateFlow<List<GithubUser>>(emptyList())
    val phoneNumber = mutableStateOf("")
    val secondTimer = mutableStateOf("")
    val clientName = mutableStateOf("")
    val sharedPreference = MySharedPreferences(application)

    init {
//        viewModelScope.launch {
//            getUsers()
//        }
    }

    private suspend fun getUsers() {
        repository.getUsers().collect {
            if (it.isSuccessful) {
                userList.value = it.body() ?: emptyList()
            }
        }
    }

    fun signInWithPhone(number: String, activity: Activity) =
        repository.createUserWithPhone(number, activity)

    fun checkSmsCode(code: String) = repository.signInWithCredential(code)

}