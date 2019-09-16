package com.zafer.mvvmzaferapp.data.network.responses

import com.zafer.mvvmzaferapp.data.db.entities.User

data class AuthResponse (

    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?

)