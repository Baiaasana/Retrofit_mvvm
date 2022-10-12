package com.example.apichat.presenter.ui.fragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apichat.domain.use_case.UserUseCase
import com.example.apichat.utility.Resource
import com.example.apichat.utility.UserViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val userUseCaseImpl: UserUseCase) :
    ViewModel() {

    private val _userFlow = MutableStateFlow<UserViewState>(UserViewState())
    val userFlow = _userFlow.asStateFlow()

    fun getInfo() {
        viewModelScope.launch {
            val data = userUseCaseImpl.invoke()
            data.collect {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        val result = it.data!!.map { userDomain ->
                            userDomain.toPresentation()
                        }
                        _userFlow.value = _userFlow.value.copy(isLoading = false, data = result)
                        Log.d("log1", " success - $result")
                    }
                    Resource.Status.ERROR -> {
                        _userFlow.value = _userFlow.value.copy(isLoading = false,
                            errorMessage = it.message.toString())
                        Log.d("log1", "error")
                    }
                    Resource.Status.LOADING -> {
                        _userFlow.value = _userFlow.value.copy(isLoading = true)
                        Log.d("log1", "loading")
                    }
                }
            }
        }
    }
}