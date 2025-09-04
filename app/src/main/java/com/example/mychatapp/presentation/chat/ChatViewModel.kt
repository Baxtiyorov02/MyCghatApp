package com.example.mychatapp.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychatapp.data.usecase.ListenToMessageUseCase
import com.example.mychatapp.data.usecase.SendMessageUseCase
import com.example.mychatapp.domain.model.MessageModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class ChatState(
    val currentMessage: String = "",
    val messages: List<MessageModel> = emptyList(),
)


class ChatViewModel(
    private val sendMessageUseCase: SendMessageUseCase,
    private val listenToMessagesUseCase: ListenToMessageUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(ChatState())
    val state = _state.asStateFlow()

    fun setMessage(message: String) {
        _state.update {
            it.copy(
                currentMessage = message
            )
        }
    }

    fun sendMessage(toUserId: String) {
        val senderId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val message = _state.value.currentMessage
        if (message.isNotEmpty()) {
            viewModelScope.launch {
                sendMessageUseCase.invoke(
                    senderId = senderId,
                    receiverId = toUserId,
                    message = message
                )
                _state.update {
                    it.copy(
                        currentMessage = ""
                    )
                }
            }
        }
    }

    fun fetchMessage(
        currentUserId: String,
        otherUserId: String,
    ) {
        viewModelScope.launch {
            listenToMessagesUseCase.invoke(
                currentUseId = currentUserId,
                otherUser = otherUserId,
                onMessageChanged = {
                    messages->
                    _state.update {
                        it.copy(
                            messages=messages
                        )
                    }
                }
            )

        }
    }
}