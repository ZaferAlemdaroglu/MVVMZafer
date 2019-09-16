package com.zafer.mvvmzaferapp.ui.home.profile

import androidx.lifecycle.ViewModel;
import com.zafer.mvvmzaferapp.data.repositories.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {


    val user = repository.getUser()


}
