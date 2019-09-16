package com.zafer.mvvmzaferapp.ui.home.quotes

import androidx.lifecycle.ViewModel;
import com.zafer.mvvmzaferapp.data.repositories.QuotesRepository
import com.zafer.mvvmzaferapp.util.lazyDeferred

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {

    val quotes by lazyDeferred(){
        repository.getQuotes()
    }


}
