package com.example.mychatapp.di

import com.example.mychatapp.data.repository.AuthRepositoryImpl
import com.example.mychatapp.data.repository.ChatRepositoryImpl
import com.example.mychatapp.data.repository.UserRepositoryImpl
import com.example.mychatapp.data.usecase.GetAllUsersUseCase
import com.example.mychatapp.data.usecase.GetLoggedInUserUseCase
import com.example.mychatapp.data.usecase.IsUserLoggedInUseCase
import com.example.mychatapp.data.usecase.ListenToMessageUseCase
import com.example.mychatapp.data.usecase.SendMessageUseCase
import com.example.mychatapp.data.usecase.SetDisplayNameUseCase
import com.example.mychatapp.data.usecase.SignInUseCase
import com.example.mychatapp.data.usecase.SignOutUseCase
import com.example.mychatapp.data.usecase.SignUpUseCase
import com.example.mychatapp.domain.repository.AuthRepository
import com.example.mychatapp.domain.repository.ChatRepository
import com.example.mychatapp.domain.repository.UserRepository
import com.example.mychatapp.presentation.auth.AuthViewModel
import com.example.mychatapp.presentation.chat.ChatViewModel
import com.example.mychatapp.presentation.home.HomeViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val  appModule= module {

    singleOf(::AuthRepositoryImpl){
        bind<AuthRepository>()
    }

    singleOf(:: UserRepositoryImpl){
        bind<UserRepository>()
    }

    singleOf(:: ChatRepositoryImpl){
        bind<ChatRepository>()
    }

    singleOf(:: IsUserLoggedInUseCase )

    singleOf(:: SignInUseCase)
    singleOf(:: SignUpUseCase)
    singleOf(:: SignOutUseCase)
    singleOf(:: GetLoggedInUserUseCase)
    singleOf(:: SetDisplayNameUseCase)
    singleOf(:: SendMessageUseCase)
    singleOf(:: ListenToMessageUseCase)
    singleOf(:: GetAllUsersUseCase)
    singleOf(:: AuthViewModel)

    singleOf(::HomeViewModel)
    singleOf(:: ChatViewModel)

}