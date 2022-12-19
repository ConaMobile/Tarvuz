package com.conamobile.tarvuz.ui.screens.main.mvvm.repository

import com.conamobile.tarvuz.ui.screens.main.mvvm.db.MainApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val repository: MainApiService,
) {
    suspend fun loadPostsDummy(page: Int, limit: Int) = repository.loadPosts(page, limit)
}