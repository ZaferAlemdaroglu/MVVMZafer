package com.zafer.mvvmzaferapp.ui.home.todoList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zafer.mvvmzaferapp.data.repositories.TodoItemsRepository

@Suppress("UNCHECKED_CAST")
class TodoListViewModelFactory(
    private val repository : TodoItemsRepository
) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TodoListViewModel(repository) as T
    }




}