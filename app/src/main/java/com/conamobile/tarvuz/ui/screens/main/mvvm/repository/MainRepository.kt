package com.conamobile.tarvuz.ui.screens.main.mvvm.repository

import com.conamobile.tarvuz.ui.screens.di.db.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun loadPosts() = flow { emit(apiService.loadPosts()) }
}