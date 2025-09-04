package com.example.mychatapp.presentation.auth.events

sealed class AuthEvent {
    data class ShowToast(val msg: String): AuthEvent()
    data object NavToHome: AuthEvent()
}