package com.zafer.mvvmzaferapp.data.repositories

import com.zafer.mvvmzaferapp.data.db.AppDatabase
import com.zafer.mvvmzaferapp.data.db.entities.User
import com.zafer.mvvmzaferapp.data.network.MyApi
import com.zafer.mvvmzaferapp.data.network.NetworkConnectionInterceptor
import com.zafer.mvvmzaferapp.data.network.SafeApiRequest
import com.zafer.mvvmzaferapp.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponse {

        return apiRequest { api.userLogin(email, password) }

    }

    suspend fun userSignup(
        name: String,
        email: String,
        password: String
    ) :AuthResponse{
        return  apiRequest { api.userSignup(name,email,password)}
    }


    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getuser()




}