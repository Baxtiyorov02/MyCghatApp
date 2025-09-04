package com.example.mychatapp.domain.repository

import android.R
import com.example.mychatapp.data.network.NetworkResponse
import com.example.mychatapp.domain.model.MessageModel

interface ChatRepository {

    suspend fun sendMessage(
        senderId: String,
        receiverId: String,
        message: String,
    ): NetworkResponse<Boolean>


    suspend fun listenToMessages(
        currentUserId: String,
        otherUserId: String,
        onMessageChanged: (List<MessageModel>) -> Unit
    )
}