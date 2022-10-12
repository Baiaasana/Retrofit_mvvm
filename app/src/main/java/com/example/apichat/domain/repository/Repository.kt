package com.example.apichat.domain.repository

import com.example.apichat.domain.model.UserDomain
import com.example.apichat.utility.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun doNetworkCall(): Flow<Resource<List<UserDomain>>>

}