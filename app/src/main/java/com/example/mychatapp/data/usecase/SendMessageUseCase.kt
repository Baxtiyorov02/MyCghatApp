package com.example.mychatapp.data.usecase

import com.example.mychatapp.data.network.NetworkResponse
import com.example.mychatapp.domain.repository.ChatRepository

class SendMessageUseCase(
    private val repository: ChatRepository
) {


    suspend fun invoke(
        senderId: String,
        receiverId: String,
        message: String,
    ): NetworkResponse<Boolean> = repository.sendMessage(
        senderId,
        receiverId,
        message
    )
}