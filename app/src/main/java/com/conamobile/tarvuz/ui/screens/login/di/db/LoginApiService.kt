package com.conamobile.tarvuz.ui.screens.login.di.db

import com.conamobile.tarvuz.ui.screens.login.di.model.GithubUser
import retrofit2.Response
import retrofit2.http.GET

interface LoginApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<GithubUser>>

}