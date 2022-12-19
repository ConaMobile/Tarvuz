package com.conamobile.tarvuz.ui.screens.main.mvvm.model

import com.squareup.moshi.Json

data class PostsDummy(
    @field:Json(name = "data")
    val users: List<DummyList>,
    @field:Json(name = "limit")
    val limit: Int,
    @field:Json(name = "page")
    val page: Int,
    @field:Json(name = "total")
    val total: Int,
)

data class DummyList(
    @field:Json(name = "firstName")
    val firstName: String,
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "lastName")
    val lastName: String,
    @field:Json(name = "picture")
    val picture: String,
    @field:Json(name = "title")
    val title: String,
)