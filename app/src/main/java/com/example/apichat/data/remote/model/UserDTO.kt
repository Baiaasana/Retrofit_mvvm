package com.example.apichat.data.remote.model

import com.example.apichat.domain.model.UserDomain
import com.squareup.moshi.Json

data class UserDTO(
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
    val updated_date: Long? = -2211764351000,
) {
    fun toDomain(): UserDomain {
        return UserDomain(
            id = id,
            email = email,
            firstName = firstName,
            lastName = lastName,
            avatar = avatar,
            messageType = messageType,
            lastMessage = lastMessage,
            unreadMessage = unreadMessage,
            isTyping = isTyping,
            updated_date = updated_date
        )
    }
}