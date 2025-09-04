package com.example.mychatapp.data.usecase

import com.example.mychatapp.data.network.NetworkResponse
import com.example.mychatapp.domain.model.UserModel
import com.example.mychatapp.domain.repository.UserRepository

class GetLoggedInUserUseCase(
    private val repository: UserRepository
) {

    suspend fun invoke(): NetworkResponse<UserModel>{
        return repository.getLoggedInUSer()
    }

}