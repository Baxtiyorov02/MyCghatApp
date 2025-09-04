package com.example.mychatapp.data.usecase

import com.example.mychatapp.domain.repository.AuthRepository

class SignOutUseCase(
    private val repository: AuthRepository
) {
    operator fun invoke(){
        repository.signOut()
    }
}