package com.example.apichat.data.remote.network

import com.example.apichat.data.remote.model.UserDTO
import com.example.apichat.utility.APIConstants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(APIConstants.END_POINT)
    suspend fun getInfo(): Response<List<UserDTO>>
}