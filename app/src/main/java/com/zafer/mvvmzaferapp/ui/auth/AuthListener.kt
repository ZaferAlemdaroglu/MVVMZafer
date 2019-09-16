package com.zafer.mvvmzaferapp.ui.auth

import androidx.lifecycle.LiveData
import com.zafer.mvvmzaferapp.data.db.entities.User

interface AuthListener {

    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message:String)

}