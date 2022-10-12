package com.example.apichat.utility

import com.example.apichat.presenter.model.UserUI

data class UserViewState(
    val isLoading: Boolean = false,
    val data: List<UserUI>? = null,
    val errorMessage: String = "",
)