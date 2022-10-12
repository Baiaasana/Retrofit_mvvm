package com.example.apichat.presenter.model

import com.squareup.moshi.Json

data class UserUI(
    val id: Int?,
    val email: String?,
    @Json(name = "first_name")
    val firstName: String?,
    @Json(name = "last_name")
    val lastName: String?,
    val avatar: String?,
    @Json(name = "message_type")
    val messageType: String?,
    @Json(name = "last_message")
    val lastMessage: String?,
    @Json(name = "unrea_message")
    val unreadMessage: Int?,
    @Json(name = "is_typing")
    val isTyping: Boolean? = false,
    @Json(name = "updated_date")
    val updated_date: Long?,
)