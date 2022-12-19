package com.conamobile.tarvuz.ui.screens.main.mvvm.impl

import com.conamobile.tarvuz.ui.screens.main.mvvm.db.MainApiService
import com.conamobile.tarvuz.ui.screens.main.mvvm.model.PostsDummy
import javax.inject.Inject

class MainApiServiceImpl @Inject constructor(
    private val api: MainApiService,
) : MainApiService {
    override suspend fun loadPosts(page: Int, limit: Int): PostsDummy {
        return api.loadPosts(page, limit)
    }
}