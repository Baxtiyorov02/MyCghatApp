package com.example.mychatapp.data.usecase

import com.example.mychatapp.data.network.NetworkResponse
import com.example.mychatapp.domain.repository.AuthRepository

class SetDisplayNameUseCase(
    private val repository: AuthRepository
) {
    suspend fun invoke(name: String): NetworkResponse<Boolean>{
        return repository.setDisplayName(name)
    }
}