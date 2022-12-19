package com.conamobile.tarvuz.ui.screens.di.db

import com.conamobile.tarvuz.ui.screens.main.mvvm.model.Posts
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun loadPosts(): Response<List<Posts>>

}