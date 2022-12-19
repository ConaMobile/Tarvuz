package com.conamobile.tarvuz.ui.screens.login.mvvm.impl

import android.app.Activity
import com.conamobile.tarvuz.ui.screens.login.mvvm.db.LoginApiService
import com.conamobile.tarvuz.ui.screens.login.mvvm.state.ResultState
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginApiServiceImpl @Inject constructor(
    private val authDb: FirebaseAuth,
) : LoginApiService {

    private lateinit var onVerificationCode: String

    override fun createUserWithPhone(phone: String, activity: Activity): Flow<ResultState<String>> =
        callbackFlow {
            trySend(ResultState.Loading)

            val onVerificationCallback =
                object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                        trySend(ResultState.Success(p0.smsCode.toString()))
                    }

                    override fun onVerificationFailed(p0: FirebaseException) {
                        trySend(ResultState.Failure(p0))
                    }

                    override fun onCodeSent(
                        verficationCode: String,
                        p1: PhoneAuthProvider.ForceResendingToken,
                    ) {
                        super.onCodeSent(verficationCode, p1)
                        trySend(ResultState.Success("Jo'natildi"))
                        onVerificationCode = verficationCode
                    }
                }

            val options = PhoneAuthOptions.newBuilder(authDb).setPhoneNumber("+998$phone")
                .setTimeout(60L, TimeUnit.SECONDS).setActivity(activity)
                .setCallbacks(onVerificationCallback).build()
            PhoneAuthProvider.verifyPhoneNumber(options)
            awaitClose {
                close()
            }
        }

    override fun signInWithCredential(otp: String): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)
        val credential = PhoneAuthProvider.getCredential(onVerificationCode, otp)
        authDb.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) trySend(ResultState.Success("Tasdiqlandi"))
        }.addOnFailureListener {
            trySend(ResultState.Failure(it))
        }
        awaitClose {
            close()
        }
    }
}