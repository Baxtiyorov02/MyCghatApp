package com.example.mychatapp.data.usecase

import com.example.mychatapp.data.network.NetworkResponse
import com.example.mychatapp.domain.model.UserModel
import com.example.mychatapp.domain.repository.UserRepository

class GetAllUsersUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(): NetworkResponse<List<UserModel>> {
        return repository.getAllUsers()
    }
}