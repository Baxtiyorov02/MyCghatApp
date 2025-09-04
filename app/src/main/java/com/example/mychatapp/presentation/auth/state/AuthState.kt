package com.example.mychatapp.presentation.auth.state

import com.example.mychatapp.data.network.NetworkResponse
import com.example.mychatapp.presentation.auth.events.AuthType
import com.google.firebase.auth.FirebaseUser

data class AuthState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val signInResponse: NetworkResponse<FirebaseUser> = NetworkResponse.Idle,
    val signUpResponse: NetworkResponse<Boolean> = NetworkResponse.Idle,
    val authType: AuthType = AuthType.SIGN_UP
) {
}