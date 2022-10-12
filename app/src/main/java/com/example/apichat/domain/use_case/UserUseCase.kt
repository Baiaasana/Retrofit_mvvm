package com.example.apichat.domain.use_case

import com.example.apichat.domain.model.UserDomain
import com.example.apichat.domain.repository.Repository
import com.example.apichat.utility.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCase @Inject constructor(private val repository: Repository) {

    suspend fun invoke(): Flow<Resource<List<UserDomain>>> {
        return repository.doNetworkCall()
    }
}