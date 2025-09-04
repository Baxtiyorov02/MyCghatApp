package com.example.mychatapp.domain.model

data class MessageModel(
    val senderId: String="",
    val receiverId: String="",
    val message: String="",
    val timesTamp: Long= System.currentTimeMillis()
)
