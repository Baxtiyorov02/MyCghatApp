package com.example.mychatapp.domain.repository

import com.example.mychatapp.data.network.NetworkResponse
import com.example.mychatapp.domain.model.UserModel

interface UserRepository {

    suspend fun getAllUsers(): NetworkResponse<List<UserModel>>

    suspend fun getLoggedInUSer(): NetworkResponse<UserModel>
}