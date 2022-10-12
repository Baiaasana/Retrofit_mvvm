package com.example.apichat.utility

object APIConstants {
    const val BASE = "https://run.mocky.io/v3/"
    const val END_POINT = "80d25aee-d9a6-4e9c-b1d1-80d2a7c979bf"
}

enum class Constants(val value: String) {
    TEXT("text"),
    ATTACHMENT("attachment"),
    VOICE("voice"),
    VOICE_MESSAGE("Sent a voice message"),
    ATTACHMENT_MESSAGE("Sent an attachment")

}