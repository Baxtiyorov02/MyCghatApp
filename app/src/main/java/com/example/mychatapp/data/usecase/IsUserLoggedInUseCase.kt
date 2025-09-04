package com.example.mychatapp.data.usecase

import com.example.mychatapp.domain.repository.AuthRepository

class IsUserLoggedInUseCase(
    private val repository: AuthRepository
) {
    operator  fun invoke(): Boolean=repository.isUserLoggedIn()
}