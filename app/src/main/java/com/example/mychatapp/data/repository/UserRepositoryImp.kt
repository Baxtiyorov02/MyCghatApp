package com.example.mychatapp.data.repository

import com.example.mychatapp.data.network.NetworkResponse
import com.example.mychatapp.domain.model.UserModel
import com.example.mychatapp.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl : UserRepository {
    private val firestore = FirebaseFirestore.getInstance()
    override suspend fun getAllUsers(): NetworkResponse<List<UserModel>> {

        return try {
            val snapshot = firestore.collection("users").get().await()
            val currentUserId = FirebaseAuth.getInstance().uid
            val users = snapshot.documents.mapNotNull { doc ->
                doc.toObject(UserModel::class.java)
            }.filter {
                it.id != currentUserId
            }
            NetworkResponse.Success(users)
        } catch (e: Exception) {
            NetworkResponse.Failure(e.localizedMessage ?: "")
        }
    }

    override suspend fun getLoggedInUSer(): NetworkResponse<UserModel> {
        return try {
            val uid = FirebaseAuth.getInstance().uid
            if (uid != null) {
                val doc = firestore.collection("users")
                    .document(uid)
                    .get()
                    .await()
                val user = doc.toObject(UserModel::class.java)
                if (user != null) {
                    return NetworkResponse.Success(user)
                } else {
                    NetworkResponse.Failure("User not found")
                }
            } else {
                NetworkResponse.Failure("User not logged in")
            }

        } catch (e: Exception) {
            NetworkResponse.Failure(e.localizedMessage ?: "Something went wrong")
        }
    }


}