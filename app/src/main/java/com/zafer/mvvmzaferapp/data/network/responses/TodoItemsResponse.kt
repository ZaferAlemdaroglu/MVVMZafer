package com.zafer.mvvmzaferapp.data.network.responses

import com.zafer.mvvmzaferapp.data.db.entities.TodoItem

data class TodoItemsResponse (
    val isSuccessful: Boolean,
    val todoItems:List<TodoItem>
)