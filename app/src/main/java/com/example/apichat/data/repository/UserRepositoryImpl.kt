package com.example.apichat.data.repository

import com.example.apichat.common.ResponseHandler
import com.example.apichat.data.remote.network.ApiService
import com.example.apichat.domain.model.UserDomain
import com.example.apichat.domain.repository.Repository
import com.example.apichat.utility.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val responseHandler: ResponseHandler,
) : Repository {

    override suspend fun doNetworkCall(): Flow<Resource<List<UserDomain>>> = flow {
        val result = responseHandler.safeApiCall { apiService.getInfo() }
        when (result.status) {
            Resource.Status.SUCCESS -> {
                emit(Resource.success(result.data!!.map { userDto ->
                    userDto.toDomain()
                }))
            }
            Resource.Status.ERROR -> {
                emit(Resource.error(result.message.toString()))
            }
            Resource.Status.LOADING -> {
                Resource.loading(null)
            }
        }
    }
}
