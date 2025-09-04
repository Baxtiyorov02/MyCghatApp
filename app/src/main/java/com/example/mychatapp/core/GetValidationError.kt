package com.example.mychatapp.core

fun getValidationError(email: String,
    password: String,
    ): String? {
    return when {
        email.isBlank() && password.isBlank() -> "Email yoki password to`ldirilmagan"
        email.isBlank() -> "Iltimos email kiriting"
        password.isBlank() -> "Iltimos password kiriting"
        password.length < 6 -> "Password kamida 6 ta belgidan iborat bolishi kerak"
        else -> null
    }
}