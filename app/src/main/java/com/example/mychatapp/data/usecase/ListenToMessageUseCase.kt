package com.example.mychatapp.data.usecase

import com.example.mychatapp.domain.model.MessageModel
import com.example.mychatapp.domain.repository.ChatRepository

class ListenToMessageUseCase(
    private val repository: ChatRepository,
) {

    suspend fun invoke(
        currentUseId: String,
        otherUser: String,
        onMessageChanged:(List<MessageModel>)-> Unit,
    ){
        repository.listenToMessages(
            currentUserId = currentUseId,
            otherUserId = otherUser,
            onMessageChanged = onMessageChanged
        )
    }
}