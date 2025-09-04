package com.example.mychatapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychatapp.data.network.NetworkResponse
import com.example.mychatapp.data.usecase.GetAllUsersUseCase
import com.example.mychatapp.data.usecase.GetLoggedInUserUseCase
import com.example.mychatapp.data.usecase.SetDisplayNameUseCase
import com.example.mychatapp.data.usecase.SignOutUseCase
import com.example.mychatapp.domain.model.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class HomeState(
    val users: NetworkResponse<List<UserModel>> = NetworkResponse.Idle,
    val loggedInUser: NetworkResponse<UserModel> = NetworkResponse.Idle,
    val name: String = "",
)

class HomeViewModel(
    private val signOutUseCase: SignOutUseCase,
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getLoggedInUserUseCase: GetLoggedInUserUseCase,
    private val setDisplayNameUseCase: SetDisplayNameUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        fetchUsers()
        fetchLoggedInUser()
    }


    fun setName(name: String) {
        _state.update {
            it.copy(
                name = name
            )
        }
    }

    fun updateName(name: String) {
        viewModelScope.launch {
            setDisplayNameUseCase.invoke(name)
            fetchLoggedInUser()
        }
    }

    fun signOut() {
        signOutUseCase.invoke()
    }

    private fun fetchLoggedInUser() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    loggedInUser = NetworkResponse.Loading
                )
            }
            val currentUser = getLoggedInUserUseCase.invoke()
            _state.update {
                it.copy(
                    loggedInUser = currentUser
                )
            }
        }
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    users = NetworkResponse.Loading
                )
            }
            val users = getAllUsersUseCase.invoke()
            _state.update {
                it.copy(
                    users = users
                )
            }
        }
    }
}