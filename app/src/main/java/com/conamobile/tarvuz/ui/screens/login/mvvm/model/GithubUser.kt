package com.conamobile.tarvuz.ui.screens.login.mvvm.model

data class GithubUser(
    val login: String,
    val avatar_url: String,
    val id: String,
    val type: String,
    val starred_url: String,
    val html_url: String,
)