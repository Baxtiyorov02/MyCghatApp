package com.example.mychatapp.data.usecase

import com.example.mychatapp.data.network.NetworkResponse
import com.example.mychatapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class SignInUseCase(
    private val repository: AuthRepository
) {
    suspend fun invoke(
        email: String,
        password: String
    ): NetworkResponse<FirebaseUser>  = repository.signIn(email,password)
}